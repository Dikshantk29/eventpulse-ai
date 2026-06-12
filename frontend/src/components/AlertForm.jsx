import { useState } from "react";

function AlertForm({ onAlertCreated }) {
  const [form, setForm] = useState({
    title: "",
    description: "",
    severity: "MEDIUM",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await onAlertCreated(form);
    setForm({ title: "", description: "", severity: "MEDIUM" });
  };

  return (
    <div style={styles.card}>
      <h2 style={styles.heading}>Create Alert</h2>

      <form onSubmit={handleSubmit}>
        <div style={styles.field}>
          <label>Title</label>
          <input
            type="text"
            name="title"
            value={form.title}
            onChange={handleChange}
            placeholder="System Failure"
            style={styles.input}
          />
        </div>

        <div style={styles.field}>
          <label>Description</label>
          <textarea
            name="description"
            value={form.description}
            onChange={handleChange}
            rows="4"
            placeholder="Describe the issue..."
            style={styles.input}
          />
        </div>

        <div style={styles.field}>
          <label>Severity</label>
          <select
            name="severity"
            value={form.severity}
            onChange={handleChange}
            style={styles.input}
          >
            <option>LOW</option>
            <option>MEDIUM</option>
            <option>HIGH</option>
            <option>CRITICAL</option>
          </select>
        </div>

        <button type="submit" style={styles.button}>
          Create Alert
        </button>
      </form>
    </div>
  );
}

const styles = {
  card: {
    background: "#111",
    border: "1px solid #333",
    padding: "30px",
    borderRadius: "14px",
    marginBottom: "30px",
  },
  heading: {
    color: "#fff",
    marginBottom: "20px",
    fontWeight: "300",
  },
  field: {
    display: "flex",
    flexDirection: "column",
    marginBottom: "20px",
    gap: "8px",
    color: "#ddd",
  },
  input: {
    padding: "14px",
    background: "#000",
    border: "1px solid #444",
    borderRadius: "8px",
    color: "#fff",
    outline: "none",
  },
  button: {
    width: "100%",
    padding: "14px",
    background: "#fff",
    color: "#000",
    border: "none",
    borderRadius: "8px",
    fontWeight: "bold",
    cursor: "pointer",
  },
};

export default AlertForm;