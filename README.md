# Turing Machine

A **Turing machine** is a mathematical model of computation that defines an abstract machine, which manipulates symbols on a strip of tape according to a table of rules. Despite the model's simplicity, given any computer algorithm, a Turing machine capable of simulating that algorithm's logic can be constructed.

The machine operates on an infinite memory tape divided into discrete cells. The machine positions its head over a cell and "reads" (scans) the symbol there. Then, as per the symbol and its present place in a finite table of user-specified instructions, the machine (i) writes a symbol (e.g., a digit or a letter from a finite alphabet) in the cell (some models allowing symbol erasure or no writing), then (ii) either moves the tape one cell left or right (some models allow no motion, some models move the head), then (iii) (as determined by the observed symbol and the machine's place in the table) either proceeds to a subsequent instruction or halts the computation.

A **Turing machine can be formally defined as a 7-tuple**:
*TM = (Q, Σ, Γ, δ, s, b, F)*
* Q: Finite set of states
* Σ: Input alphabet, compound by terminal symbols
* Γ: Tape alphabet, compound by non terminal symbols
* δ: Finite set of transitions between states
* s: s ∈ Q is the initial state
* b: b ∈ Γ is the blank symbol (the only symbol allowed to occur on the tape infinitely often at any step during the computation)
* F: F ⊆ Q is the set of accepting states