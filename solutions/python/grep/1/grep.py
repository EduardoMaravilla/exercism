def grep(pattern, flags, files):
    flags_list = flags.split()

    output = []

    for file_name in files:
        with open(file_name, "r") as f:
            lines = f.readlines()

            for line_num, line in enumerate(lines, start=1):
                clean_line = line.rstrip('\n')

                search_term = pattern
                match_line = clean_line

                if "-i" in flags_list:
                    search_term = search_term.lower()
                    match_line = match_line.lower()

                if "-x" in flags_list:
                    is_match = (search_term == match_line)
                else:
                    is_match = (search_term in match_line)

                if "-v" in flags_list:
                    is_match = not is_match


                if is_match:

                    if "-l" in flags_list:
                        output.append(f"{file_name}\n")
                        break

                    res = ""

                    if len(files) > 1:
                        res += f"{file_name}:"

                    if "-n" in flags_list:
                        res += f"{line_num}:"

                    res += line
                    output.append(res)

    return "".join(output)
