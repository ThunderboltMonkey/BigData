# INVESTIGATION 1: What is the pair coding?

The __"Pair Coding"__ or __"Pair Programming"__ is an agile technique that originates from extreme programming (XP) in which two developers come together and work at a workstation to design, code and test user stories. One, the __driver__, writes code while the other, the observer or __navigator__, reviews each line of code as it is written while providing tactical and analytical feedback. The observer considers the "strategic" direction of the work, presenting ideas for improvements and likely future problems to address. And the two programmers change roles frequently.

This methodology is presented in __3 other styles__, in addition to the driver-browser:

1. Backseat Navigator: This style is similar to the controller-navigator, but the navigator assumes more of the tactical roles of the driver. In backseat navigation, the controller continues to control the keyboard and typing, but the browser dictates syntactic instructions, such as what name to call a variable or what specific method to call. The rear seat navigator style works best with a beginner as a driver and an expert as a navigator, allowing the beginner to learn by doing.

2. Ping Pong Pairing: Another style of pair programming that is often used in development is ping pong pairing. In this pattern, the first person writes a test that is currently failing and the second person gets the test to pass. Then the second person writes a failed test and the first person gets it to pass.

    The benefits of ping pong matchmaking are that it allows roles to change frequently and forces engineers to pay attention to the coding and testing aspects of development, gaining familiarity with TDD in the process.

3. Pomodoro: The Pomodoro matchmaking style is similar to ping-pong match, but prescribes set time intervals for each session. A typical Pomodoro-style matchmaking session lasts 25 minutes followed by a 5-minute break. The controller and the browser change positions. After four 25-minute sessions, both programmers take a longer 20-minute break. Forced breaks and regular position change help ensure both programmers are always productive, focused, and up-to-date when a session begins.

While it is true that this trend or methodology raises doubts, only those who have been able to apply it are those who can offer a positive or negative comment on said methodology.
Some will be in favor and give some of the following arguments:
- Increases productivity.
- Programmers learn to work as a team.
- Communication flows more often and more efficiently.
- Continuous peer learning is created and it is easier for a new team member to quickly get to work and get to know the environment.

While others might have the following negative opinions about pair programming:
- Two programmers work together much faster than one alone, but not faster than two programmers working separately.
- Increase costs (Human resource).
- Problems with each other may slow down your results.

In conclusion I must say that now that I have to use this technique it has seemed quite enjoyable since I can socialize with people I did not know and learn from them or share a little of what I know with them and help each other to meet the objectives of the matter.

# INVESTIGATION 2: What is the Pearson correlation?

## DEFINITION

Pearson's correlation coefficient is a test that measures the statistical relationship between two continuous variables. If the association between the elements is not linear, then the coefficient is not adequately represented.

## SUMMARY

The correlation coefficient can take a range of values from +1 to -1. A value of 0 indicates that there is no association between the two variables. A value greater than 0 indicates a positive association. That is, as the value of one variable increases, so does the value of the other. A value less than 0 indicates a negative association; that is, as the value of one variable increases, the value of the other decreases.

## RESTRICTIONS

To carry out the Pearson correlation it is necessary to fulfill the following:

- The measurement scale must be an interval or ratio scale.
- The variables must be roughly distributed.
- The association must be linear.
- There should be no outliers in the data.

## HOW IS IT CALCULATED?

The formulas for calculating the Pearson correlation coefficient are as follows:

![Pearson formulas](https://github.com/ThunderboltMonkey/BigData/blob/unit_1/Research/Castillo%20Solis%20Fabian%20Eduardo/Pearson.png)

Where: __"x"__ is equal to variable number one, __"y"__ belongs to variable number two, __"zx"__ is the standard deviation of variable one, __"zy"__ is the standard deviation of variable two and __"N"__ is is number of data.

## INTERPRETATION

Pearson's correlation coefficient has the objective of indicating how associated two variables are with each other, therefore:

1. Correlation less than zero: If the correlation is less than zero, it means that it is negative, that is, that the variables are inversely related.

    When the value of some variable is high, the value of the other variable is low. The closer it is to -1, the clearer the extreme covariance. If the coefficient is equal to -1, we mean a perfect negative correlation.

2. Correlation greater than zero: If the correlation is equal to +1 it means that it is perfect positive. In this case it means that the correlation is positive, that is, that the variables are directly correlated.

    When the value of one variable is high, the value of the other is also high, the same happens when they are low. If it is close to +1, the coefficient will be the covariance.

3. Correlation equal to zero: When the correlation is equal to zero, it means that it is not possible to determine some sense of covariation. However, it does not mean that there is no nonlinear relationship between the variables.

    When the variables are independent it means that they are correlated, but this does not mean that the result is true.

## ADVANTAGES

Among the main advantages of Pearson's correlation coefficient are:

- The value is independent of whatever unit is used to measure the variables.
- If the sample is large, the accuracy of the estimate is more likely.

## DISADVANTAGES

Some of the disadvantages of the correlation coefficient are:

- The two variables need to be measured at a continuous quantitative level.
- The distribution of the variables must be similar to the normal curve.