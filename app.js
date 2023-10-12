const express = require('express');
const axios = require('axios');
const os = require('os');
const fs = require('fs');
const config = require('./config.json'); // Import configuration
const app = express();
const port = 3000;
const productsApiBaseUri = config.productsApiBaseUri;

app.set('view engine', 'ejs');
app.use(express.static('public'));

// Static Middleware
app.use('/static', express.static('public'));



// Endpoint to serve product data to client
app.get('/api/products', async (req, res) => {
  try {
    let response = await axios.get(`${productsApiBaseUri}/api/products`);
    res.json(response.data);
  } catch (error) {
    console.error('Error fetching products:', error);
    res.status(500).send('Error fetching products');
  }
});

app.get('/', (req, res) => {
  // Gather system info
  const systemInfo = {
    hostname: os.hostname(),
    ipAddress: getIPAddress(),
    isContainer: isContainer(),
    isKubernetes: fs.existsSync('/var/run/secrets/kubernetes.io')
    // ... any additional system info here
  };

  res.render('index', {
    systemInfo: systemInfo
  });
});

function getIPAddress() {
  // Logic to fetch IP Address
  const networkInterfaces = os.networkInterfaces();
  return (networkInterfaces['eth0'] && networkInterfaces['eth0'][0].address) || 'IP not found';
}

function isContainer() {
  // Logic to check if running in a container
  try {
    fs.readFileSync('/proc/1/cgroup');
    return true;
  } catch (e) {
    return false;
  }
}

app.listen(port, () => {
    console.log(`App listening at http://localhost:${port}`);
});

