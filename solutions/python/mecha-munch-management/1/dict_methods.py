"""Functions to manage a users shopping cart items."""


def add_item(current_cart, items_to_add):
    """Add items to shopping cart.

    :param current_cart: dict - the current shopping cart.
    :param items_to_add: iterable - items to add to the cart.
    :return: dict - the updated user cart dictionary.
    """

    for item in items_to_add:
        if item not in current_cart:
            current_cart[item] = 1
        else:
            current_cart[item] += 1

    return current_cart


def read_notes(notes):
    """Create user cart from an iterable notes entry.

    :param notes: iterable of items to add to cart.
    :return: dict - a user shopping cart dictionary.
    """
    user_cart = {}
    for note in notes:
        if note not in user_cart:
            user_cart[note] = 1
        else:
            user_cart[note] += 1
    return user_cart


def update_recipes(ideas, recipe_updates):
    """Update the recipe ideas dictionary.

    :param ideas: dict - The "recipe ideas" dict.
    :param recipe_updates: iterable -  with updates for the ideas section.
    :return: dict - updated "recipe ideas" dict.
    """

    for recipe in recipe_updates:
        ideas[recipe[0]] = recipe[1]


    return ideas


def sort_entries(cart):
    """Sort a users shopping cart in alphabetically order.

    :param cart: dict - a users shopping cart dictionary.
    :return: dict - users shopping cart sorted in alphabetical order.
    """

    return dict(sorted(cart.items()))


def send_to_store(cart, aisle_mapping):
    """Combine users order to aisle and refrigeration information.

    :param cart: dict - users shopping cart dictionary.
    :param aisle_mapping: dict - aisle and refrigeration information dictionary.
    :return: dict - fulfillment dictionary ready to send to store.
    """

    result = {}
    for name, value in cart.items():
        result[name] = [value,aisle_mapping[name][0],aisle_mapping[name][1]]

    return dict(sorted(result.items(),reverse=True))


def update_store_inventory(fulfillment_cart, store_inventory):
    """Update store inventory levels with user order.

    :param fulfillment_cart: dict - fulfillment cart to send to store.
    :param store_inventory: dict - store available inventory
    :return: dict - store_inventory updated.
    """

    for item, (qty_cart, _, _) in fulfillment_cart.items():
        if item in store_inventory:
            qty_store, aisle, flag = store_inventory[item]

            new_qty = qty_store - qty_cart

            if new_qty == 0:
                store_inventory[item] = ["Out of Stock", aisle, flag]
            else:
                store_inventory[item] = [new_qty, aisle, flag]

    return store_inventory
