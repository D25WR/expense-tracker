import React from "react";
import { useAuth } from "../context/AuthContext";

export default function Navbar() {
  const { user, logout } = useAuth();
  return (
    <nav className="navbar">
      <strong>💰 Expense Tracker</strong>
      <div className="user-controls">
        <span>{user?.fullName}</span>
        <button onClick={logout}>Logout</button>
      </div>
    </nav>
  );
}
