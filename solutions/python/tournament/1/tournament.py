def tally(rows):
    result = ["Team                           | MP |  W |  D |  L |  P"]
    teams = {}
    for game in rows:
        parts = game.split(";")
        for team in parts[:2]:
            if team not in teams:
                teams[team] = {"mp": 0, "win": 0, "draw": 0, "loss": 0, "points": 0}

        teams[parts[0]]["mp"] += 1
        teams[parts[1]]["mp"] += 1

        if parts[2] == "win":
            teams[parts[0]]["win"] += 1
            teams[parts[1]]["loss"] += 1
        elif parts[2] == "loss":
            teams[parts[0]]["loss"] += 1
            teams[parts[1]]["win"] += 1
        else:
            teams[parts[0]]["draw"] += 1
            teams[parts[1]]["draw"] += 1

        for team in parts[:2]:
            teams[team]["points"] = teams[team]["win"] * 3 + teams[team]["draw"]

    for name, stats in sorted(teams.items(), key=lambda item: (-item[1]["points"], item[0])):
        result.append(
            f"{name:<31}|  {stats["mp"]:<2}|  {stats["win"]:<2}|  {stats["draw"]:<2}|  "
            f"{stats["loss"]:<2}|{" " if stats["points"] < 10 else ""} {stats["points"]:<1}")

    return result
