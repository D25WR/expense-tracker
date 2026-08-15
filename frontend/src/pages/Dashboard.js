import React, { useEffect, useState } from "react";
import api from "../services/api";
import ExpenseForm from "../components/ExpenseForm";
import ExpenseList from "../components/ExpenseList";
import { PieChart, Pie, Cell, Tooltip, ResponsiveContainer } from "recharts";

const COLORS = ["#6C5CE7", "#00B894", "#FDCB6E", "#E17055", "#0984E3", "#D63031"];

export default function Dashboard() {
  const [expenses, setExpenses] = useState([]);
  const [categories, setCategories] = useState([]);

  const loadData = async () => {
    const [expRes, catRes] = await Promise.all([
      api.get("/expenses"),
      api.get("/categories"),
    ]);
    setExpenses(expRes.data);
    setCategories(catRes.data);
  };

  useEffect(() => { loadData(); }, []);

  const chartData = Object.values(
    expenses.reduce((acc, e) => {
      const key = e.categoryName || "Uncategorized";
      acc[key] = acc[key] || { name: key, value: 0 };
      acc[key].value += e.amount;
      return acc;
    }, {})
  );

  const total = expenses.reduce((sum, e) => sum + Number(e.amount), 0);

  return (
    <div className="page-container">
      <h1>Your Spending, at a Glance</h1>
      <p>Total spent: <strong>₹{total.toFixed(2)}</strong></p>

      {chartData.length > 0 && (
        <div className="chart-wrap">
          <ResponsiveContainer width="100%" height={280}>
            <PieChart>
              <Pie data={chartData} dataKey="value" nameKey="name" outerRadius={90} label>
                {chartData.map((_, i) => <Cell key={i} fill={COLORS[i % COLORS.length]} />)}
              </Pie>
              <Tooltip />
            </PieChart>
          </ResponsiveContainer>
        </div>
      )}

      <ExpenseForm categories={categories} onAdded={loadData} />
      <ExpenseList expenses={expenses} onChanged={loadData} />
    </div>
  );
}
