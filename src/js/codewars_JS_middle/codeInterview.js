const users = [
        {id: 1, name: "John Doe", location: "NYC"},
        {id: 659, name: "Jane Doe", location: "London"}
    ];
const admins = [
        {id: 1, isAdmin: true},
        {id: 659, isAdmin: true}
    ]; // isAdmin could be false

function displayAdmins(users, admins, location) {
    const adminsNames = [];
    const adminsMap = convertAdminsToMap(admins); // M

    users.forEach(user => {
        if(adminsMap[user.id] && user.location === location) {
            adminsNames.push(user.name);
        }
    }); //N
    
    displayAdminsFound(adminsNames, location);
}

function displayAdminsFound(adminsNames, location) {
    let displayStr = 'Admins in ' + location + ' are ' + adminsNames.join(', '); //K

    console.log(displayStr);
}

function convertAdminsToMap(admins) {
    const map = {};

    admins.forEach(admin => {
        map[admin.id] = admin.isAdmin;
    });

    return map;
}
// Admins in London are Jane Doe, Anna Frank, Mike Fisher