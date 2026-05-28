from collections import deque


def simulate_game(pA, pB):
    player_a = deque(pA)
    player_b = deque(pB)
    stack = []
    game_tricks = 0
    total_played = 0
    total_cards = len(player_a) + len(player_b)

    penalties = {"J": 1, "Q": 2, "K": 3, "A": 4}
    turn = "A"
    visited_states = set()

    def get_signature():
        norm_a = "".join("N" if c not in penalties else c for c in player_a)
        norm_b = "".join("N" if c not in penalties else c for c in player_b)
        return f"{norm_a}|{norm_b}|{turn}"

    while True:
        state = get_signature()
        if state in visited_states:
            return {"status": "loop", "cards": total_played, "tricks": game_tricks}
        visited_states.add(state)

        current_deck = player_a if turn == "A" else player_b
        if not current_deck:
            break

        card = current_deck.popleft()
        total_played += 1
        stack.append(card)

        if card in penalties:
            is_penalty_active = True
            target = "B" if turn == "A" else "A"
            count = penalties[card]

            while is_penalty_active:
                responded = False
                opponent_deck = player_a if target == "A" else player_b

                for _ in range(count):
                    if not opponent_deck:
                        is_penalty_active = False
                        break

                    p_card = opponent_deck.popleft()
                    total_played += 1
                    stack.append(p_card)

                    if p_card in penalties:
                        count = penalties[p_card]
                        target = "B" if target == "A" else "A"
                        responded = True
                        break

                if not responded:
                    is_penalty_active = False
                    winner_name = "B" if target == "A" else "A"
                    (player_a if winner_name == "A" else player_b).extend(stack)
                    stack = []
                    game_tricks += 1
                    turn = winner_name
        else:
            turn = "B" if turn == "A" else "A"

        if len(player_a) == total_cards or len(player_b) == total_cards:
            break

    if stack:
        game_tricks += 1

    return {"status": "finished", "cards": total_played, "tricks": game_tricks}
