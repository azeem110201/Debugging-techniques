const messageTypes = { LEFT: "left", RIGHT: "right", LOGIN: "login" };

//Chat stuff
const chatWindow = document.getElementById("chat");
const messagesList = document.getElementById("messagesList");
const messageInput = document.getElementById("messageInput");
const sendBtn = document.getElementById("sendBtn");

//login stuff
let username = "";
const usernameInput = document.getElementById("usernameInput");
const loginBtn = document.getElementById("loginBtn");
const loginWindow = document.getElementById("login");
const chatRoom = document.getElementById("chat-room");

const messages = []; // { author, date, content, type }

//Connect to socket.io - automatically tries to connect on same port app was served from
var socket = io();

let chatFlag = false;

chatRoom.addEventListener("change", (e) => {
  if (e.target.value === "chat-room-1")
    socket.off("chat-room-2").on("chat-room-1", (message) => {
      console.log(`chatroom 1 b4"`);
      addMessage(message);
      console.log(`chatroom 1 after"`);
    });
  else
    socket.off("chat-room-1").on("chat-room-2", (message) => {
      console.log(`chatroom 2 b4"`);
      if (chatFlag !== true) addMessage(message);
      console.log(`chatroom 2 after"`);
    });
});

addMessage = (message) => {
  //Update type of message based on username
  // console.log(message.type)
  if (message.type !== messageTypes.LOGIN) {
    // console.log(username)
    // console.log(message.author);
    if (message.author === username) {
      message.type = messageTypes.RIGHT;
    } else {
      message.type = messageTypes.LEFT;
    }
  }

  messages.push(message);
  displayMessages();

  //scroll to the bottom
  chatWindow.scrollTop = chatWindow.scrollHeight;
};

createMessageHTML = (message) => {
  if (message.type === messageTypes.LOGIN) {
    // console.log(message.author);
    return `
			<p class="secondary-text text-center mb-2">${message.author} joined the chat...</p>
		`;
  }
  return `
	<div class="message ${
    message.type === messageTypes.LEFT ? "message-left" : "message-right"
  }">
		<div class="message-details flex">
			<p class="flex-grow-1 message-author">${
        message.type === messageTypes.LEFT ? message.author : ""
      }</p>
			<p class="message-date">${message.date}</p>
		</div>
		<p class="message-content">${message.content}</p>
	</div>
	`;
};

displayMessages = () => {
  const messagesHTML = messages
    .map((message) => createMessageHTML(message))
    .join("");
  messagesList.innerHTML = messagesHTML;
};

sendBtn.addEventListener("click", (e) => {
  e.preventDefault();
  if (!messageInput.value) {
    return console.log("Invalid input");
  }

  const date = new Date();
  const month = ("0" + date.getMonth()).slice(0, 2);
  const day = date.getDate();
  const year = date.getFullYear();
  const dateString = `${month}/${day}/${year}`;

  const message = {
    author: username,
    date: dateString,
    content: messageInput.value,
  };
  sendMessage(message);
  //clear input
  messageInput.value = "";
});

loginBtn.addEventListener("click", (e) => {
  e.preventDefault();
  chatFlag = true;
  socket.on(chatRoom.value, (message) => {
    console.log(`chatroom before"`);

    addMessage(message);
    console.log(`chatroom after"`);
  });

  if (!usernameInput.value) {
    return console.log("Must supply a username");
  }

  //set the username and create logged in message
  username = usernameInput.value;
  // console.log(username)
  sendMessage({ author: username, type: messageTypes.LOGIN });

  //show chat window and hide login
  loginWindow.classList.add("hidden");
  chatWindow.classList.remove("hidden");
});

sendMessage = (message) => {
  socket.emit(chatRoom.value, message);
  //   console.log(chatRoom.value)
  // console.log(message)
  
};