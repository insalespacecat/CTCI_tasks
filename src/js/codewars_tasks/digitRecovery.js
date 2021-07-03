//Digit Recovery

//Some letters in the input string are representing a written-out digit.
//Some of the letters may randomly shuffled. Your task is to recover them all.

//Note that:
//Only consecutive letters can be used. "OTNE" cannot be recovered to 1!
//Every letter has to start with an increasing index.. "ONENO" results to 11, because the E can be used two times. Endless loops are not possible!

//If there are letters in the string, which don't create a number you can ignore them.
//If no digits can be found, return "No digits found"

//Take care about the order! "ENOWT" will be recovered to 12 and not to 21.
//The input string consists only UpperCase letters
//e.g.
//recover("NEO") =>  "1"
//recover("ONETWO") => "12"
//recover("ONENO") => "11"
//recover("TWWTONE") => "21"
//recover("ZYX") => "No digits found"
//recover("NEOTWONEINEIGHTOWSVEEN") => "12219827"
//You can use the following preloaded dictionary in your solution:
//const alph = {"ZERO":0,"ONE":1,"TWO":2,"THREE":3,"FOUR":4,"FIVE":5,"SIX":6,"SEVEN":7,"EIGHT":8,"NINE":9};


// Sliding window approach
// Min: 3 char, max: 5 char.
// example: ONEIGHT TWOONE THREEFIV NEOTWONEINEIGHTOWSVEEN
// i = 0 j = 3 (start with 3); j++, j++ (expand to 5); i++, j-- (shrink to 3); j++ j++
// the algorithm is: start with 3 -> expand to 5 -> shrink to 3 -> expand to 5 -> ...
// on every step check for occurency: either plain or permutation one.
function recover(str){

    if(str.length < 3) {
        return "No digits found";
    }

    let answer = '';
    let sD = sDMapConstruct();
    let i = 0;
    let j = 3;
    let ss = str.substring(i, j);

    answer = checkSubstring(ss, answer, sD);

    if(j < str.length) {
        j++;
        ss = str.substring(i, j);
        answer = checkSubstring(ss, answer, sD);
    }

    if(j < str.length) {
        j++;
        ss = str.substring(i, j);
        answer = checkSubstring(ss, answer, sD);
    }

    if(i < str.length) {
        i++;
        ss = str.substring(i, j);
        answer = checkSubstring(ss, answer, sD);
    }

    j--;
    ss = str.substring(i, j);
    answer = checkSubstring(ss, answer, sD);

    while(i < str.length && j < str.length && j - i > 2) {

        if(j < str.length) {
            j++;
        }

        if(j < str.length) {
            j++;
            ss = str.substring(i, j);
            answer = checkSubstring(ss, answer, sD);
        }

        if(i < str.length) {
            i++;
            ss = str.substring(i, j);
            answer = checkSubstring(ss, answer, sD);
        }

        j--;
        ss = str.substring(i, j);
        answer = checkSubstring(ss, answer, sD);

    }

    if(answer === '') {
        return 'No digits found';
    }

    return answer;

}

function checkEverySDforPerm(str) {
    return Object.keys(alph).find(element => {
        return checkPermutation(element, str)? true : false;
    });
}

function checkSubstring(ss, answer, sD) {

    let mapRes = sD.get(ss);

    if(mapRes) {
        answer += alph[ss];
        return answer;
    }

    let permRes = checkEverySDforPerm(ss);

    if(permRes) {
        answer += alph[permRes];
        return answer;
    }

    return answer;

}

function checkPermutation(str1, str2) {
    const str1Arr = str1.split('');
    const str2Arr = str2.split('');

    if(str1Arr.length !== str2Arr.length) {
        return false;
    }

    const strCompArr1 = {};
    const strCompArr2 = {};

    for(let i = 0; i < str1Arr.length; i++) {
        strCompArr1[str1Arr[i]]? strCompArr1[str1Arr[i]]++ : strCompArr1[str1Arr[i]] = 1;
        strCompArr2[str2Arr[i]]? strCompArr2[str2Arr[i]]++ : strCompArr2[str2Arr[i]] = 1;
    }

    for(let key of Object.keys(strCompArr1)) {
        if(strCompArr2[key] !== strCompArr1[key]) {
            return false;
        }
    }

    return true;
}

function sDMapConstruct() {
    const map = new Map();
    map.set('ZERO', true);
    map.set('ONE', true);
    map.set('TWO', true);
    map.set('THREE', true);
    map.set('FOUR', true);
    map.set('FIVE', true);
    map.set('SIX', true);
    map.set('SEVEN', true);
    map.set('EIGHT', true);
    map.set('NINE', true);
    return map;
}