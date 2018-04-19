package BackEnd

import javax.validation.constraints.Null

interface BackEnd_API {

    fun newClient(name:String,password:String):User{
        val x = Client(name,password)
        ClientList.put(x.username,x)
        return x
    }

    fun getClient(name:String):User?{
        if (ClientList.contains(name)) return ClientList.get(name)
        else return null
    }

    fun usernameAvailable(name:String): Boolean{
        return ClientList.contains(name)
    }
}