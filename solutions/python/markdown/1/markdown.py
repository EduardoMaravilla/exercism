import re


def parse(markdown):
    lines = markdown.split('\n')
    result = []
    in_list = False

    for line in lines:
        # 1. Handle Headers (h1 to h6)
        line = parse_headers(line)

        # 2. Handle List Items (*)
        list_match = re.match(r'\* (.*)', line)
        if list_match:
            content = parse_inline_styles(list_match.group(1))
            if not in_list:
                in_list = True
                line = f"<ul><li>{content}</li>"
            else:
                line = f"<li>{content}</li>"
        else:
            # If we were in a list and the current line is NOT a list item, close the list
            if in_list:
                result[-1] += "</ul>"
                in_list = False

            # 3. Handle Paragraphs (if not already a header)
            if not re.match(r'<h\d>', line):
                line = f"<p>{parse_inline_styles(line)}</p>"
            else:
                # Still need to parse bold/italic inside headers
                line = parse_inline_styles(line)

        result.append(line)

    # Close list if the markdown ends while still inside one
    if in_list:
        result[-1] += "</ul>"

    return "".join(result)


def parse_headers(line):
    """Checks for # prefixes and wraps content in corresponding <h> tags."""
    match = re.match(r'(#{1,6}) (.*)', line)
    if match:
        level = len(match.group(1))
        content = match.group(2)
        return f"<h{level}>{content}</h{level}>"
    return line


def parse_inline_styles(text):
    """Replaces markdown symbols for bold (__) and italic (_) with HTML tags."""
    # Bold: __text__ -> <strong>text</strong>
    text = re.sub(r'__(.*?)__', r'<strong>\1</strong>', text)
    # Italic: _text_ -> <em>text</em>
    text = re.sub(r'_(.*?)_', r'<em>\1</em>', text)
    return text