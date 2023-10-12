from flask import Flask, jsonify, render_template
from datetime import datetime
import socket
import os
import json

app = Flask(__name__)

# Load product data from JSON file
with open('products.json', 'r') as f:
    products = json.load(f)


@app.route('/')
def home():
    system_info = get_system_info()
    with open("config.json", "r") as config_file:
      config_data = json.load(config_file) 
    app_version = config_data.get("app_version", "N/A")  # Default to "N/A" if no version is found
    return render_template('index.html', current_year=datetime.now().year, system_info=system_info, version=app_version)

@app.route('/api/products', methods=['GET'])

def get_products():
    return jsonify(products), 200


@app.route('/api/products/<int:product_id>', methods=['GET'])
def get_product(product_id):
    product = next((product for product in products if product['id'] == product_id), None)
    if product is not None:
        return jsonify(product)
    else:
        return jsonify({'message': 'Product not found'}), 404

def get_system_info():
    hostname = socket.gethostname()
    ip_address = socket.gethostbyname(hostname)

    # Additional logic for container and Kubernetes check
    is_container = os.path.exists('/.dockerenv')
    is_kubernetes = os.path.exists('/var/run/secrets/kubernetes.io/serviceaccount')
    
    return {
        "hostname": hostname,
        "ip_address": ip_address,
        "is_container": is_container,
        "is_kubernetes": is_kubernetes
    }


if __name__ == "__main__":
    app.run(debug=True)

