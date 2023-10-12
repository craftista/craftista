const express = require('express');
const axios = require('axios');
const os = require('os');
const fs = require('fs');
const config = require('./config.json'); // Import configuration
const app = express();
const port = 3000;

app.set('view engine', 'ejs');
app.use(express.static('public'));

// Static Middleware
app.use('/static', express.static('public'));

const getSystemInfo = () => {
    return {
        hostname: os.hostname(),
        ipAddress: os.networkInterfaces()['eth0'] ? os.networkInterfaces()['eth0'][0].address : 'N/A',
        isContainer: fs.existsSync('/.dockerenv') || fs.existsSync('/.dockerinit'),
        isKubernetes: fs.existsSync('/var/run/secrets/kubernetes.io')
    };
};


app.get('/', async (req, res) => {
    try {
        // Fetch products from the backend API using the URL from config
        let products = await axios.get(config.backendApiUrl);
        products = products.data;

        const systemInfo = getSystemInfo();
        res.render('index', { systemInfo, products });
    } catch (error) {
        console.error("Error fetching products or other external data:", error);
        
        const systemInfo = getSystemInfo();
        res.render('index', { systemInfo, products: [] });
    }
});

app.listen(port, () => {
    console.log(`App listening at http://localhost:${port}`);
});

