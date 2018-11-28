const URL = "http://localhost:8080/BookerBackend/api/ticket/alltickets"

function handleHttpErrors(res) {
    if (!res.ok) {
      return Promise.reject({ status: res.status, fullError: res.json() })
    }
    return res.json();
   }
   
  export default class TicketFacade {
    makeOptions(method,addToken,body) {
        var opts = {
          method: method,
          headers: {
            "Content-type": "application/json",
            'Accept': 'application/json',
          }
        }
    }

    fetchTicket = () => {
        const options = this.makeOptions("GET",true); //True add's the token
        return fetch(URL, options).then(res => res.json(),handleHttpErrors);
      }
      
   }
   const facade = new TicketFacade();
  

   
