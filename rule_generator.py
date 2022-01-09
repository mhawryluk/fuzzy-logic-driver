coinDistHs = ['far' + dir for dir in ('Left', 'Right')]
coinDistHs += ['close' + dir for dir in ('Left', 'Right')]
coinDistHs += ['veryClose' + dir for dir in ('Left', 'Right')]

coinDists = ['far', 'close']
chaserDists = ['far', 'close']

chaserDistHs = ['veryCloseLeft', 'closeLeft', 'far', 'closeRight', 'veryCloseRight']

ruleNo = 0


def printRule(coinDist, chaserDist, coinDistH, chaserDistH, velocityChange):
    global ruleNo
    print(f'RULE {ruleNo}: IF coinDist IS {coinDist} AND chaserDist IS {chaserDist} AND coinDistH IS {coinDistH} AND chaserDistH IS {chaserDistH} THEN velocityChange is {velocityChange};')
    ruleNo += 1


for coinDist in coinDists:
    for chaserDist in chaserDists:
        for coinDistH in coinDistHs:
            for chaserDistH in chaserDistHs:
                velocityChange = 'stay'

                if chaserDist == 'far':
                    if coinDistH == 'veryCloseRight': velocityChange = 'right'
                    elif coinDistH == 'veryCloseLeft': velocityChange = 'left'
                    elif coinDistH == 'closeRight': velocityChange = 'strongRight'
                    elif coinDistH == 'closeLeft': velocityChange = 'strongLeft'
                    elif coinDistH == 'farRight': velocityChange = 'strongRight'
                    elif coinDistH == 'farLeft': velocityChange = 'strongLeft'

                elif coinDist == 'far':
                    if chaserDistH == 'veryCloseRight': velocityChange = 'strongLeft'
                    elif chaserDistH == 'closeRight': velocityChange = 'strongLeft'
                    elif chaserDistH == 'far': velocityChange = 'stay'
                    elif chaserDistH == 'closeLeft': velocityChange = 'strongRight'
                    elif chaserDistH == 'veryCloseLeft': velocityChange = 'strongRight'

                else:
                    if chaserDistH == 'veryCloseRight' and coinDistH == 'veryCloseRight':
                        velocityChange = 'left'
                    elif chaserDistH == 'veryCloseLeft' and coinDistH == 'veryCloseLeft':
                        velocityChange = 'right'
                    elif chaserDistH == 'veryCloseLeft' and coinDistH == 'closeLeft':
                        velocityChange = 'left'
                    elif chaserDistH == 'veryCloseRight' and coinDistH == 'closeRight':
                        velocityChange = 'right'
                    elif chaserDistH == 'closeLeft' and coinDistH == 'veryCloseLeft':
                        velocityChange = 'right'
                    elif chaserDistH == 'closeRight' and coinDistH == 'veryCloseRight':
                        velocityChange = 'left'

                    elif chaserDistH.endswith('Right'):
                        velocityChange = 'left'
                    
                    elif chaserDist.endswith('Left'):
                        velocityChange = 'right'

                    elif coinDist.endswith('Left'):
                        velocityChange = 'left'

                    elif coinDist.endswith('Right'):
                        velocityChange = 'right'
                    
                
                printRule(coinDist, chaserDist, coinDistH, chaserDistH, velocityChange)
