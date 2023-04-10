const express = require('express');
const mongoose = require('mongoose');
const app = express();

// middleware every request will be passed through this middleware 
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const MONGODB_URL = 'mongodb+srv://gnana:gnana2004@cluster0.yct3a1k.mongodb.net/test';

mongoose.connect(MONGODB_URL).then(() => {
    console.log('Connected to MongoDB');
}).catch((err) => {
    console.log('Error while connecting to MongoDB');
});

// schema
const userSchema = new mongoose.Schema({
    name:{
        type:String,
        require:true,
    },
    email:{
        type:String,
        require:true,
        unique:true,
    },
    phone:{
        type:Number,
        unique:true,
        require:true,
    },
    password:{
        type:String,
        unique:true,
        require:true,
    },
    item:{
        type:String,
        unique:true,
        require:true,
    }
});

// model -  used to perform CRUD operations
const User = mongoose.model('user', userSchema);

// model -  used to perform CRUD operations

app.get('/', (req, res) => {
    res.sendFile(__dirname + '/About.html');
})

// CRUD Operations

// Create
app.post('/adduser', async (req, res) => {
    const username = req.body.name;
    const mail = req.body.email;
    const phoneno = req.body.phone;
    const passwd = req.body.password;
    const items = req.body.item;
    // create a new user
    const user = new User({
        name: username,
        email:mail,
        phone:phoneno,
        password:passwd,
        Item:items
    });
    // saving the user to the database
    await user.save();
    res.send('Item added successfully');
})

// Read
app.get('/getusers', async (req, res) => {
    try {
        const users = await User.find(); // return all the users
        res.send(users);
    } catch (err) {
        console.log(err);
        res.send('Error while getting items');
    }
})

// Update
app.post('/update', async (req, res) => {
    try {
        const username = req.body.name;
        await User.updateOne({ name: username }, { name: 'Ritesh Kumar' });
        res.send('Item updated successfully');
    } catch (err) {
        console.log(err);
        res.send('Error while updating Item');
    }
});

// Delete
app.delete('/delete', async (req, res) => {
    try {
        console.log(req.body);
        const username = req.body.name;
        await User.deleteOne({ name: username });
        res.send('Item deleted successfully');
    } catch (err) {
        console.log(err);
        res.send('Error while deleting item');
    }
});

app.listen(3005, () => {
    console.log('Server is running on port 3005');
})