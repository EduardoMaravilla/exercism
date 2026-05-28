PROTEINS={"G":"C","C":"G","T":"A","A":"U"}

def to_rna(dna_strand):
    return "".join([PROTEINS[dna] for dna in dna_strand])
