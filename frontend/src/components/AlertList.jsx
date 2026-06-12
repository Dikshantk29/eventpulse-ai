
import AlertCard from './AlertCard';

function AlertList({ alerts, onDelete }) {

  if (alerts.length === 0) {
    return (
      <div style={{ textAlign: 'center', color: '#6b7280', padding: '40px' }}>
        No alerts yet. Create one above.
      </div>
    );
  }

  return (
    <div>
      <h2 style={{ color: 'white', marginBottom: '16px' }}>
        Alerts ({alerts.length})
      </h2>
      {alerts.map(alert => (
        <AlertCard
          key={alert.id}
          alert={alert}
          onDelete={onDelete}
        />
      ))}
    </div>
  );
}

export default AlertList;