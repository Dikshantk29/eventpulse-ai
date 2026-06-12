function AlertCard({ alert, onDelete }) {
  return (
    <div style={styles.card}>
      <div style={styles.top}>
        <span style={styles.badge}>{alert.severity}</span>
        <span style={styles.status}>{alert.status}</span>

        <button onClick={() => onDelete(alert.id)} style={styles.deleteBtn}>
          ×
        </button>
      </div>

      <h3 style={styles.title}>{alert.title}</h3>
      <p style={styles.description}>{alert.description}</p>

      <small style={styles.time}>
        {alert.createdAt
          ? new Date(alert.createdAt).toLocaleString()
          : "No time"}
      </small>
    </div>
  );
}

const styles = {
  card: {
    background: "#111",
    border: "1px solid #2a2a2a",
    padding: "20px",
    borderRadius: "12px",
    marginBottom: "14px",
  },
  top: {
    display: "flex",
    alignItems: "center",
    marginBottom: "12px",
    gap: "10px",
  },
  badge: {
    background: "#fff",
    color: "#000",
    padding: "4px 12px",
    borderRadius: "20px",
    fontSize: "12px",
    fontWeight: "bold",
  },
  status: {
    border: "1px solid #555",
    padding: "4px 10px",
    borderRadius: "20px",
    fontSize: "12px",
    color: "#ccc",
  },
  deleteBtn: {
    marginLeft: "auto",
    background: "transparent",
    border: "none",
    color: "#fff",
    fontSize: "20px",
    cursor: "pointer",
  },
  title: {
    color: "#fff",
    marginBottom: "8px",
  },
  description: {
    color: "#aaa",
    marginBottom: "10px",
  },
  time: {
    color: "#666",
  },
};

export default AlertCard;