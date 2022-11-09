import { createContext, useContext, useEffect, useState } from "react";
import { USER_TYPE } from '../constant/user-type'

const Context = createContext();

export function UserProvider({ children }) {
    const [user, setUser] = useState({
        id: null,
        type: [],
        name: '',
        address:'',
        phone:'',
        token: '',
        numberCartItems: 0
    });


    useEffect(()=> {
        const userJson = sessionStorage.getItem('user');
        if(userJson) {
            setUser(JSON.parse(userJson))
        }
    }, [])
    return (
        <Context.Provider value={[user, setUser]}>{children}</Context.Provider>
    );
}

export function useUserContext() {
    return useContext(Context);
}