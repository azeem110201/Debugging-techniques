const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);
const path = require('path');

//Serve public directory
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
	res.sendFile(path.join(__dirname, +'public/index.html'));
});

io.on('connection', socket => {
	console.log('a user connected');

	socket.on('disconnect', () => {
		console.log('user disconnected');
	});

	socket.on('chat-room-1', message => {
		message["room"] = 'chat-room-1';
		//Broadcast the message to everyone
		io.emit('chat-room-1', message);
	});

	socket.on('chat-room-2', message => {
		message.room = 'chat-room-2';
		//Broadcast the message to everyone
		io.emit('chat-room-2', message);
	});
});

http.listen(3000, () => {
	console.log('listening on port 3000');
});
