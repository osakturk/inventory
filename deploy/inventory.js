db = db.getSiblingDB('inventory');
db.createUser(
    {
        user: 'root', 
        pwd: 'root', 
        roles: ['readWrite']
    }
);
