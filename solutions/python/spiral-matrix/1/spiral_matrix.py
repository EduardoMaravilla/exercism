def spiral_matrix(size):
    matrix = [[0] * size for _ in range(size)]
    counter = 1

    top,bottom,left,right = 0,size-1,0,size-1

    while counter <= size**2:
        for i in range(left,right+1):
            matrix[top][i] = counter
            counter += 1
        top += 1

        for i in range(top,bottom+1):
            matrix[i][right] = counter
            counter += 1
        right -= 1

        if top <= bottom:
            for i in range(right,left-1,-1):
                matrix[bottom][i] = counter
                counter += 1
            bottom -= 1

        if left <= right:
            for i in range(bottom,top-1,-1):
                matrix[i][left] = counter
                counter += 1
            left += 1

    return matrix

