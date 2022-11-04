import { createContext, useContext, useState } from "react";
import { USER_TYPE } from '../constant/user-type'

const Context = createContext();

export function UserProvider({ children }) {
    const [user, setUser] = useState({
        id: null,
        type: USER_TYPE.ADMIN
    });
    return (
        <Context.Provider value={[user, setUser]}>{children}</Context.Provider>
    );
}

export function useUserContext() {
    return useContext(Context);
}