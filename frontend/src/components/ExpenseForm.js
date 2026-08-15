import React, { useState } from "react";
import api from "../services/api";

export default function ExpenseForm({ categories, onAdded }) {
  const [form, setForm] = useState({ amount: "", note: "", date: "", categoryId: "" });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await api.post("/expenses", {
      ...form,
      amount: parseFloat(form.amount),
      categoryId: form.categoryId || null,
    });
    setForm({ amount: "", note: "", date: "", categoryId: "" });
    onAdded();
  };

  return (
    <form onSubmit={handleSubmit} className="expense-form">
      <input type="number" step="0.01" placeholder="Amount" required
        value={form.amount} onChange={(e) => setForm({ ...form, amount: e.target.value })} />
      <input type="text" placeholder="Note" value={form.note}
        onChange={(e) => setForm({ ...form, note: e.target.value })} />
      <input type="date" required value={form.date}
        onChange={(e) => setForm({ ...form, date: e.target.value })} />
      <select value={form.categoryId} onChange={(e) => setForm({ ...form, categoryId: e.target.value })}>
        <option value="">Uncategorized</option>
        {categories.map((c) => <option key={c.id} value={c.id}>{c.name}</option>)}
      </select>
      <button type="submit">Add Expense</button>
    </form>
  );
}
