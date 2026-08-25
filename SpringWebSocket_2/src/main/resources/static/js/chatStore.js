const {defineStore}=Pinia
/*
    stompClient.send("/app/chat.private")
         |
	@MessageMapping("/chat.private")
	     |
	privateMessage()
	     |
    message.getReceiver() 확인 (받는 사람)
	     |
  /queue/private/"+message.getReceiver() : 구독한 사용자 전송
  
    stompClient.send("/app/chat.send")
	     |
   @MessageMapping("/chat.send")
         |
	  sendMessage() 
	     |
	  return message 
	     |
	  @sendTo("/topic/public")
	     |
	  접속자 전체 전송 
         
*/
const useChatStore=defineStore('chat',{
	// 저장(데이터 = 전역)
	state:()=>({
		stompClient:null,
		userId:'',
		message:[],
		msg:'',
		receiver:''
	}),
	actions:{
		// 연결후에 데이터를 받는 브라우저를 지정 URI
		connect(){
			const socket=new SockJS('/ws-chat')
			this.stompClient=Stomp.over(socket)
			
			this.stompClient.connect({},()=>{
				this.stompClient.subscribe('/queue/private/'+this.userId,(msg)=>{
					this.message.push(JSON.parse(msg.body))
				})
			})
			console.log(this.message)
		},
		send(){
			this.stompClient.send('/app/chat.private/',
				  {},JSON.stringify({
				sender:this.userId,
				receiver:this.receiver,
				message:this.msg
			}))
		}
	}
	
})