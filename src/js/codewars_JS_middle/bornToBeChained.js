//In this kata, we will implement a function that allows us to perform this by applying a fluid style:
//c.sum(4, 5).sum(5).minus(4).sum(7).addOne().double().double().execute(); // 72
//Your job is implement the chain function:
//function chain(fns) {
//}
//
// var c = chain({sum: sum, minus: minus, double: double, addOne: addOne});

function sum(x, y) {
    console.log('sum x:' + this.x);
    if(!y) {
        return this.x + x;
    }
    return x + y;
}

function double(x) {
    console.log('double x:' + this.x);
    if(!x) {
        return sum(this.x, this.x);
    }
    return sum(x, x);
}

function minus (x, y) {
    console.log('minus x:' + this.x);
    if(!y) {
        return this.x - x;
    }
    return x - y;
}

function addOne(x) {
    console.log('add one x:' + this.x);
    if(!x) {
        return sum(this.x, 1);
    }
    return sum(x, 1);
}

function chain(fns) {
    let context = {x: 0};
    let callStack = [];

    for(let key in fns) {
        context[key] = (o, p) => {
            if(p) {
                callStack.push(() => {context.x = fns[key].call(context, o, p)});
            } else {
                callStack.push(() => {context.x = fns[key].call(context, o)});
            }
            return context;
        }
    }

    context.execute = () => {
        callStack.forEach(value => {
            value();
        });

        return context.x;
    };

    return context;
}

var c = chain({sum: sum, minus: minus, double: double, addOne: addOne});