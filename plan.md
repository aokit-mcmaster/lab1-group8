# Proposed plan
## Part 1
We can split up the stateflows and recombine them later. I think we should implement an enable signal for each individual stateflow
so that it only outputs pulses when enable is HIGH. AOO/VOO don't monitor anything so output is all that matters

**We have to figure out what the output of these stateflows should be: is it driving a pin? Is it sending data directly? I'm thinking
probably each should send out a signal that says send pulse now? Still not too sure**

IDEA: We fill out this table so that each stateflow generally matches the others

Note*: Make each separate state have a different led colour turn on as an output, just so its easier to identify the states we're in when testing on the physical board.

Stateflow | Inputs | Condition(s) | Outputs
--------- | ------ | -------| -------
AOO | enable, parameters |  | ?
VOO | enable, parameters |  | ?
AAI | enable, heart signals, parameters | Start atrial escape interval timer after p-wave is sensed. Reset timer after next occurring p-wave. Initiate pacer(spike) activity ONLY if no p wave is detected within set "atrial escape interval". | ?
VVI | enable, heart signals, parameters | Start ventricle escape interval timer on qrs wave. Reset after next qrs wave. If no qrs wave occurs within timer interval, initiate pacer(aka spike). | ?

