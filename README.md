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

## Program description
The program creates a Turing machine from a configuration files (see the examples in Samples folder). Then the programs simulates the behaviour of the TM with some input string (given in a file or via command line). Lastly, the program says if the TM finishes its simulation in an accepting state or not, and shows the contents of the first tape (input tape) from the header to the right. In addition, the program can be executed in trace mode. You can create multitape Turing machines if the configuration file transitions are well formed.
* **The input string must be specified with each symbol separated with a `whitespace`**.
* **In trace mode, the character `>` tells the symbol pointed by a tape header**.

## Execution

For execute the program try the command below:
```
    java -jar TMSimulator.jar configFile traceMode [inputFile]
```
Where:
* **configFile** is the file containing the configuration of the Turing machine (e.g Samples/TM2.txt)
* **traceMode** if you want to enable it type 1, otherwise type 0
* **inputFile** is the file containing the input string (optional)

## Trace mode execution example

```
    java -jar TMSimulator.jar Samples/TM2.txt 1 input.txt

    Input string: a a a a b b b b c c c c

    TM=(Q, Σ, Γ, δ, s, b, F)

      Q = { q0, q1, q2, q3}
      Σ = { a, b, c}
      Γ = { ., a, b, c}
      s = q0
      b = .
      F = { q3}
      δ = {
        (q0, [a, ., .], q0, [a, a, .], [RIGHT, RIGHT, STAY]),
        (q0, [b, ., .], q1, [b, ., b], [RIGHT, STAY, RIGHT]),
        (q1, [b, ., .], q1, [b, ., b], [RIGHT, STAY, RIGHT]),
        (q1, [c, ., .], q2, [c, ., .], [STAY, LEFT, LEFT]),
        (q2, [c, a, b], q2, [c, ., .], [RIGHT, LEFT, LEFT]),
        (q2, [., ., .], q3, [., ., .], [STAY, STAY, STAY])
      }


    ==========================
    ...| >a | a | a | a | b | b | b | b | c | c | c | c |...
    ...| >. ...
    ...| >. ...
    State: q0
    Input symbols: [a, ., .]
    Transition to apply: (q0, [a, ., .], q0, [a, a, .], [RIGHT, RIGHT, STAY])
    ==========================
    ...| a | >a | a | a | b | b | b | b | c | c | c | c |...
    ...| a | >. |...
    ...| >. |...
    State: q0
    Input symbols: [a, ., .]
    Transition to apply: (q0, [a, ., .], q0, [a, a, .], [RIGHT, RIGHT, STAY])
    ==========================
    ...| a | a | >a | a | b | b | b | b | c | c | c | c |...
    ...| a | a | >. |...
    ...| >. |...
    State: q0
    Input symbols: [a, ., .]
    Transition to apply: (q0, [a, ., .], q0, [a, a, .], [RIGHT, RIGHT, STAY])
    ==========================
    ...| a | a | a | >a | b | b | b | b | c | c | c | c |...
    ...| a | a | a | >. |...
    ...| >. |...
    State: q0
    Input symbols: [a, ., .]
    Transition to apply: (q0, [a, ., .], q0, [a, a, .], [RIGHT, RIGHT, STAY])
    ==========================
    ...| a | a | a | a | >b | b | b | b | c | c | c | c |...
    ...| a | a | a | a | >. |...
    ...| >. |...
    State: q0
    Input symbols: [b, ., .]
    Transition to apply: (q0, [a, ., .], q0, [a, a, .], [RIGHT, RIGHT, STAY])
    ==========================
    ...| a | a | a | a | b | >b | b | b | c | c | c | c |...
    ...| a | a | a | a | >. |...
    ...| b | >. |...
    State: q1
    Input symbols: [b, ., .]
    Transition to apply: (q0, [b, ., .], q1, [b, ., b], [RIGHT, STAY, RIGHT])
    ==========================
    ...| a | a | a | a | b | b | >b | b | c | c | c | c |...
    ...| a | a | a | a | >. |...
    ...| b | b | >. |...
    State: q1
    Input symbols: [b, ., .]
    Transition to apply: (q1, [b, ., .], q1, [b, ., b], [RIGHT, STAY, RIGHT])
    ==========================
    ...| a | a | a | a | b | b | b | >b | c | c | c | c |...
    ...| a | a | a | a | >. |...
    ...| b | b | b | >. |...
    State: q1
    Input symbols: [b, ., .]
    Transition to apply: (q1, [b, ., .], q1, [b, ., b], [RIGHT, STAY, RIGHT])
    ==========================
    ...| a | a | a | a | b | b | b | b | >c | c | c | c |...
    ...| a | a | a | a | >. |...
    ...| b | b | b | b | >. |...
    State: q1
    Input symbols: [c, ., .]
    Transition to apply: (q1, [b, ., .], q1, [b, ., b], [RIGHT, STAY, RIGHT])
    ==========================
    ...| a | a | a | a | b | b | b | b | >c | c | c | c |...
    ...| a | a | a | >a | . |...
    ...| b | b | b | >b | . |...
    State: q2
    Input symbols: [c, a, b]
    Transition to apply: (q1, [c, ., .], q2, [c, ., .], [STAY, LEFT, LEFT])
    ==========================
    ...| a | a | a | a | b | b | b | b | c | >c | c | c |...
    ...| a | a | >a | . | . |...
    ...| b | b | >b | . | . |...
    State: q2
    Input symbols: [c, a, b]
    Transition to apply: (q2, [c, a, b], q2, [c, ., .], [RIGHT, LEFT, LEFT])
    ==========================
    ...| a | a | a | a | b | b | b | b | c | c | >c | c |...
    ...| a | >a | . | . | . |...
    ...| b | >b | . | . | . |...
    State: q2
    Input symbols: [c, a, b]
    Transition to apply: (q2, [c, a, b], q2, [c, ., .], [RIGHT, LEFT, LEFT])
    ==========================
    ...| a | a | a | a | b | b | b | b | c | c | c | >c |...
    ...| >a | . | . | . | . |...
    ...| >b | . | . | . | . |...
    State: q2
    Input symbols: [c, a, b]
    Transition to apply: (q2, [c, a, b], q2, [c, ., .], [RIGHT, LEFT, LEFT])
    ==========================
    ...| a | a | a | a | b | b | b | b | c | c | c | c | >. |...
    ...| >. | . | . | . | . | . |...
    ...| >. | . | . | . | . | . |...
    State: q2
    Input symbols: [., ., .]
    Transition to apply: (q2, [c, a, b], q2, [c, ., .], [RIGHT, LEFT, LEFT])
    ==========================
    ...| a | a | a | a | b | b | b | b | c | c | c | c | >. |...
    ...| >. | . | . | . | . | . |...
    ...| >. | . | . | . | . | . |...
    State: q3
    Input symbols: [., ., .]
    Transition to apply: (q2, [., ., .], q3, [., ., .], [STAY, STAY, STAY])
    ==========================


    Simulation finished in an accepting state
    ...| >. | ...
```