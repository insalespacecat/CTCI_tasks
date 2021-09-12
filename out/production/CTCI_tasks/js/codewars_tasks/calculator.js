//Create a simple calculator that given a string of operators (), +, -, *, / and numbers separated by spaces returns the value of that expression
// Example:
// Calculator().evaluate("2 / 2 + 3 * 4 - 6") # => 7
// Remember about the order of operations! Multiplications and divisions have a higher priority and should be performed left-to-right.
// Additions and subtractions have a lower priority and should also be performed left-to-right.

//I think that recursion approach will be much better here
//I think that recursion approach will be much better here
const Calculator = function() {
    this.evaluate = (str) => {

        if(str.length === 0) {
            return str;
        }

        //Operators and operands
        const opAndOps = str.split(' ');
        console.log(opAndOps);

        if(opAndOps.length === 1) {
            return str;
        }

        const priority = operationsPriority();

        let i = 1;
        let accum = opAndOps[0];

        while(i < opAndOps.length) {
            //next step: i += 2;
            console.log(i);
            if(priority[opAndOps[i]] === 2) {
                accum = stringOperatorsCalculator(accum, opAndOps[i+1], opAndOps[i]);
                i += 2;
            } else {
                //subAccum. Just go forward and calculate subaccum
                //until we hit priority 1. When we hit priority 1,
                //accum op subAccum

                if(i + 2 < opAndOps.length) {
                    let subI = i + 2;
                    let subAccum = opAndOps[i+1];

                    while(subI < opAndOps.length) {
                        console.log(subI);
                        if(priority[opAndOps[subI]] === 2) {
                            subAccum = stringOperatorsCalculator(subAccum, opAndOps[subI+1], opAndOps[subI]);
                            if(subI + 2 < opAndOps.length) {
                                subI += 2;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    accum = stringOperatorsCalculator(accum, subAccum, opAndOps[i]);
                    i = subI;
                } else {
                    accum = stringOperatorsCalculator(accum, opAndOps[i+1], opAndOps[i]);
                    i += 2;
                }
            }
        }

        return accum;
    }
};

//Examples:
//2 / 2 + 3 * 4 - 6
//2 + 2 / 3 / 3 * 6

//Algorithm
//If we have priority 2 -> execute immidiately
//If we have priority 1 -> check next operation
//case: priority === 1 -> execute immidiately
//case priority === 2 -> execute next op, check next operation
//repeat

//Edge cases:
//Basic:
//string is empty (length === 0)
//string is one number (length === 1)
//In the cycle:
//Stop the check on last operand if(i+2 < opAndOps.length) {i += 2}

function stringOperatorsCalculator(opOne, opTwo, operator) {
    switch(operator) {
        case '*':
            console.log(opOne + ' * ' + opTwo);
            opOne *= opTwo;
            break;
        case '/':
            console.log(opOne + ' / ' + opTwo);
            opOne /= opTwo;
            break;
        case '+':
            console.log(parseFloat(opOne, 10) + ' + ' + parseFloat(opTwo, 10))
            opOne = parseFloat(opOne, 10) + parseFloat(opTwo, 10);
            break;
        case '-':
            console.log(opOne + ' - ' + opTwo);
            opOne -= opTwo;
            break;
    }

    return opOne;
}

function operationsPriority() {
    return {
        '+': 1,
        '-': 1,
        '*': 2,
        '/': 2,
    }
}