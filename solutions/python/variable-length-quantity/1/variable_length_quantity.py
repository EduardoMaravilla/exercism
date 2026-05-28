def encode(numbers):
    encoded = []
    for byte in numbers:
        temp = [byte & 0x7f]
        byte >>= 7
        while byte > 0:
            temp.append(byte & 0x7f | 0x80)
            byte >>= 7
        encoded.extend(reversed(temp))
    return encoded

def decode(bytes_):
    if bytes_[len(bytes_)-1] & 0x80 != 0:
        raise ValueError("incomplete sequence")
    decoded = []
    number = 0
    for byte in bytes_:
        number <<= 7
        number += byte & 0x7f
        if byte & 0x80 == 0:
            decoded.append(number)
            number = 0
    return decoded
