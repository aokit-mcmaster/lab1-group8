# Proposed plan
## Part 1
We can split up the stateflows and recombine them later. I think we should implement an enable signal for each individual stateflow
so that it only outputs pulses when enable is HIGH. AOO/VOO don't monitor anything so output is all that matters

**We have to figure out what the output of these stateflows should be: is it driving a pin? Is it sending data directly? I'm thinking
probably each should send out a signal that says send pulse now? Still not too sure**

IDEA: We fill out this table so that each stateflow generally matches the others

Stateflow | Inputs | Outputs
--------- | ------ | -------
AOO | enable, parameters | ?
VOO | enable, parameters | ?
AAI | enable, heart signals, parameters | ?
VVI | enable, heart signals, parameters | ?
