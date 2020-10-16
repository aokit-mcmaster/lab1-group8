
# Module design

## OVERALL DESIGN

![](https://github.com/aokit-mcmaster/lab1-group8/blob/master/images/MASTER-diagram.png)

## UTILITIES

### PACE
| Inputs | p\_mode, p\_...\_pulse\_amplitude, p\_...\_pulse\_width, start\_pace|
| --- | --- |
| Outputs | HARDWARE\_PINS |

![](https://github.com/aokit-mcmaster/lab1-group8/blob/master/images/PACE-state-diagram.png)

### SENSE
| Inputs | p\_mode, p\_...\_sensitivity, p\_arp, p\_vrp, p\_pvarp |
| --- | --- |
| Outputs | h\_...\_pulse\_detect |

![](https://github.com/aokit-mcmaster/lab1-group8/blob/master/images/SENSE-state-diagram.png)

## MODES

### VOO
| Inputs | p\_lower\_rate\_limit, p\_upper\_rate\_limit |
| --- | --- |
| Outputs | start_pace |

### AOO
| Inputs | p\_lower\_rate\_limit, p\_upper\_rate\_limit |
| --- | --- |
| Outputs | start_pace |

### VVI
| Inputs | p\_lower\_rate\_limit, p\_upper\_rate\_limit, p\_hysteresis\_enable, p\_hysteresis\_rate\_limit, p\_rate\_smoothing\_enable, p\_rate\_smoothing\_percent |
| --- | --- |
| Outputs | start_pace |

### AAI
| Inputs | p\_lower\_rate\_limit, p\_upper\_rate\_limit, p\_hysteresis\_enable, p\_hysteresis\_rate\_limit, p\_rate\_smoothing\_enable, p\_rate\_smoothing\_percent |
| --- | --- |
| Outputs | start_pace |

## Variable Names

**p\_parameters**

- p\_mode
- p\_lower\_rate\_limit
- p\_upper\_rate\_limit
- p\_atr\_pulse\_amplitude
- p\_vent\_pulse\_amplitude
- p\_atr\_pulse\_width
- p\_vent\_pulse\_width
- p\_atr\_sensitivity
- p\_vent\_sensitivity
- p\_vrp
- p\_arp
- p\_pvarp
- p\_hysteresis\_enable
- p\_hysteresis\_rate\_limit
- p\_rate\_smoothing\_enable
- p\_rate\_smoothing\_percent

**h\_parameters**

- h\_atr\_pulse\_detect
- h\_vent\_pulse\_detect

**internal signals**

- start\_pace
