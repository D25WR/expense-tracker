import React from "react";
import api from "../services/api";

export default function ExpenseList({ expenses, onChanged }) {
  const handleDelete = async (id) => {
    await api.delete(`/expenses/${id}`);
    onChanged();
  };

  return (
    <table className="responsive-table">
      <thead>
        <tr>
          <th>Date</th><th>Note</th><th>Category</th><th>Amount</th><th></th>
        </tr>
      </thead>
      <tbody>
        {expenses.map((e) => (
          <tr key={e.id}>
            <td data-label="Date">{e.date}</td>
            <td data-label="Note">{e.note}</td>
            <td data-label="Category">{e.categoryName}</td>
            <td data-label="Amount">₹{e.amount}</td>
            <td data-label="Action"><button onClick={() => handleDelete(e.id)}>Delete</button></td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
