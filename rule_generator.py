coinDistHs = ['farLeft', 'farRight', 'closeLeft',
              'closeRight', 'veryCloseLeft', 'veryCloseRight']
obstacleDistHs = ['veryCloseLeft', 'closeLeft',
                  'far', 'closeRight', 'veryCloseRight']

coinDists = ['far', 'close']
obstacleDists = ['far', 'close']


ruleNo = 0
rules = set()


def printRule(coinDist, obstacleDist, coinDistH, obstacleDistH, velocityChange):
    global ruleNo

    if (coinDist, obstacleDist, coinDistH, obstacleDistH) in rules:
        return
    rules.add((coinDist, obstacleDist, coinDistH, obstacleDistH))

    rule = f'RULE {ruleNo}: IF '
    if coinDist is not None:
        rule += f'coinDist IS {coinDist} '

    if obstacleDist is not None:
        if not rule.endswith('IF '):
            rule += 'AND '
        rule += f'obstacleDist IS {obstacleDist} '

    if coinDistH is not None:
        if not rule.endswith('IF '):
            rule += 'AND '
        rule += f'coinDistH IS {coinDistH} '

    if obstacleDistH is not None:
        if not rule.endswith('IF '):
            rule += 'AND '
        rule += f'obstacleDistH IS {obstacleDistH} '

    rule += f'THEN velocityChange is {velocityChange};'
    print(rule)
    ruleNo += 1


for coinDist in coinDists:
    for obstacleDist in obstacleDists:
        for coinDistH in coinDistHs:
            for obstacleDistH in obstacleDistHs:
                velocityChange = 'stay'
                coinDist_ = coinDist
                coinDistH_ = coinDistH
                obstacleDist_ = obstacleDist
                obstacleDistH_ = obstacleDistH

                # jeśli przeszkoda jest daleko, podążaj za monetą
                # im jest dalej w tym kierunku, tym składowa na tym kierunku
                # powinna być większa
                if obstacleDist == 'far':
                    if coinDistH == 'veryCloseRight':
                        velocityChange = 'right'
                    elif coinDistH == 'veryCloseLeft':
                        velocityChange = 'left'
                    elif coinDistH == 'closeRight':
                        velocityChange = 'strongRight'
                    elif coinDistH == 'closeLeft':
                        velocityChange = 'strongLeft'
                    elif coinDistH == 'farRight':
                        velocityChange = 'strongRight'
                    elif coinDistH == 'farLeft':
                        velocityChange = 'strongLeft'

                    coinDist_ = None
                    obstacleDistH_ = None

                # jeśli przeszkoda jest blisko, a moneta daleko
                # to odskocz mocno od przeszkody
                elif coinDist == 'far':
                    if obstacleDistH == 'veryCloseRight':
                        velocityChange = 'strongLeft'
                    elif obstacleDistH == 'closeRight':
                        velocityChange = 'strongLeft'
                    elif obstacleDistH == 'far':  # raczej się nie zdarzy
                        velocityChange = 'stay'
                    elif obstacleDistH == 'closeLeft':
                        velocityChange = 'strongRight'
                    elif obstacleDistH == 'veryCloseLeft':
                        velocityChange = 'strongRight'

                    coinDistH_ = None

                else:  # i przeszkoda, i moneta są blisko obiektu
                    if obstacleDistH == 'veryCloseRight' and coinDistH == 'veryCloseRight':
                        velocityChange = 'left'  # jak obie są blisko to bezpieczniej odskoczyć
                    elif obstacleDistH == 'veryCloseLeft' and coinDistH == 'veryCloseLeft':
                        velocityChange = 'right'  # symetrycznie do tego co powyżej
                    elif obstacleDistH == 'veryCloseLeft' and coinDistH == 'closeLeft':
                        velocityChange = 'right'  # przeszkoda bliżej niż moneta
                    elif obstacleDistH == 'veryCloseRight' and coinDistH == 'closeRight':
                        velocityChange = 'left'  # przeszkoda bliżej niż moneta
                    elif obstacleDistH == 'closeLeft' and coinDistH == 'veryCloseLeft':
                        velocityChange = 'left'  # moneta bliżej niż przeszkoda
                    elif obstacleDistH == 'closeRight' and coinDistH == 'veryCloseRight':
                        velocityChange = 'right'  # moneta bliżej niż przeszkoda

                    # odskocz od przeszkody
                    elif obstacleDistH.endswith('Right'):
                        velocityChange = 'left'

                    # odskocz od przeszkody
                    elif obstacleDistH.endswith('Left'):
                        velocityChange = 'right'

                    elif coinDistH.endswith('Left'):  # podążaj za monetą
                        velocityChange = 'left'

                    elif coinDistH.endswith('Right'):  # podążaj za monetą
                        velocityChange = 'right'

                printRule(coinDist_, obstacleDist_, coinDistH_,
                          obstacleDistH_, velocityChange)
