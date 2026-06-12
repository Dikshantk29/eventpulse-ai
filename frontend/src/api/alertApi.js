// src/api/alertApi.js
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/alerts';

export const getAlerts = () =>
  axios.get(BASE_URL);

export const createAlert = (data) =>
  axios.post(BASE_URL, data);

export const deleteAlert = (id) =>
  axios.delete(`${BASE_URL}/${id}`);