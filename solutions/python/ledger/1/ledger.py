from datetime import datetime


class LedgerEntry:
    def __init__(self, date, description, change):
        self.date = datetime.strptime(date, '%Y-%m-%d')
        self.description = description
        self.change = change


def create_entry(date, description, change):
    return LedgerEntry(date, description, change)


def format_entries(currency, locale, entries):
    symbol = '$' if currency == 'USD' else u'€'

    if locale == 'en_US':
        headers = ['Date', 'Description', 'Change']
        date_fmt = '%m/%d/%Y'
    else:
        headers = ['Datum', 'Omschrijving', 'Verandering']
        date_fmt = '%d-%m-%Y'
    table = f"{headers[0]:<10} | {headers[1]:<25} | {headers[2]:<13}"

    sorted_entries = sorted(entries, key=lambda e: (e.date, e.description, e.change))

    for entry in sorted_entries:
        date_str = entry.date.strftime(date_fmt)

        desc = entry.description
        if len(desc) > 25:
            desc = desc[:22] + "..."

        val = abs(entry.change) / 100.0

        if locale == 'en_US':
            main_str = f"{symbol}{val:,.2f}"
            if entry.change < 0:
                change_str = f"({main_str})"
            else:
                change_str = f"{main_str} "
        else:
            formatted_val = f"{val:,.2f}".replace(',', 'X').replace('.', ',').replace('X', '.')
            sign = "-" if entry.change < 0 else ""
            change_str = f"{symbol} {sign}{formatted_val} "

        table += f"\n{date_str} | {desc:<25} | {change_str:>13}"

    return table