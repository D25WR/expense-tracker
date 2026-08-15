import React, { createContext, useContext, useState } from "react";
import api from "../services/api";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const stored = localStorage.getItem("user");
    return stored ? JSON.parse(stored) : null;
  });

  const login = async (email, password) => {
    const { data } = await api.post("/auth/login", { email, password });
    localStorage.setItem("token", data.token);
    localStorage.setItem("user", JSON.stringify({ fullName: data.fullName, email: data.email }));
    setUser({ fullName: data.fullName, email: data.email });
  };

  const register = async (fullName, email, password) => {
    const { data } = await api.post("/auth/register", { fullName, email, password });
    localStorage.setItem("token", data.token);
    localStorage.setItem("user", JSON.stringify({ fullName: data.fullName, email: data.email }));
    setUser({ fullName: data.fullName, email: data.email });
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => useContext(AuthContext);
