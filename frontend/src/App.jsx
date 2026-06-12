import { useState, useEffect } from "react";
import { getAlerts, createAlert, deleteAlert } from "./api/alertApi";
import AlertForm from "./components/AlertForm";
import AlertList from "./components/AlertList";

function App() {
  const [alerts, setAlerts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Load alerts when app starts
  useEffect(() => {
    fetchAlerts();
  }, []);

  const fetchAlerts = async () => {
    try {
      const response = await getAlerts();
      setAlerts(response.data);
    } catch (err) {
      setError("Could not connect to backend. Is it running?");
    } finally {
      setLoading(false);
    }
  };

  const handleCreateAlert = async (formData) => {
    try {
      const response = await createAlert(formData);
      setAlerts([response.data, ...alerts]); // add to top of list
    } catch (err) {
      setError("Failed to create alert.");
    }
  };

  const handleDeleteAlert = async (id) => {
    try {
      await deleteAlert(id);
      setAlerts(alerts.filter((a) => a.id !== id)); // remove from list
    } catch (err) {
      setError("Failed to delete alert.");
    }
  };

  return (
    <div style={styles.page}>
      <div style={styles.container}>
        {/* Header */}
        <div style={styles.header}>
          <h1 style={styles.logo}>⚡ EventPulse AI</h1>
          <p style={styles.subtitle}>Alert Management System</p>
        </div>

        {/* Error Banner */}
        {error && (
          <div style={styles.error}>
            {error}
            <button onClick={() => setError(null)} style={styles.closeBtn}>
              ✕
            </button>
          </div>
        )}

        {/* Create Form */}
        <AlertForm onAlertCreated={handleCreateAlert} />

        {/* Alert List */}
        {loading ? (
          <p style={{ color: "#6b7280", textAlign: "center" }}>Loading...</p>
        ) : (
          <AlertList alerts={alerts} onDelete={handleDeleteAlert} />
        )}
      </div>
    </div>
  );
}

const styles = {
  page: {
    minHeight: "100vh",
    background: "#000",
    padding: "40px",
    fontFamily: "Inter, sans-serif",
  },
  container: {
    maxWidth: "800px",
    margin: "0 auto",
  },
  header: {
    textAlign: "center",
    marginBottom: "50px",
  },
  logo: {
    color: "#fff",
    fontSize: "42px",
    fontWeight: "200",
    letterSpacing: "-1px",
  },
  subtitle: {
    color: "#777",
    fontSize: "16px",
  },
  error: {
    background: "#111",
    border: "1px solid #333",
    color: "#fff",
    padding: "15px",
    marginBottom: "20px",
    borderRadius: "8px",
  },
};
export default App;
