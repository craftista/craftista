from flask import Flask, jsonify, render_template
from datetime import datetime
import socket
import os
import json
import psycopg2

app = Flask(__name__)

# Load product data from JSON file
with open('products.json', 'r') as f:
    products = json.load(f)
with open("config.json", "r") as config_file:
    config_data = json.load(config_file) 

def get_db_connection():
    conn = psycopg2.connect(host=config_data.get("db_host"),
                            database=config_data.get("db_name"),
                            user=config_data.get("db_user"),
                            password=config_data.get("db_password")
                           )
    return conn

@app.route('/')
def home():
    system_info = get_system_info()
    app_version = config_data.get("app_version", "N/A")  # Default to "N/A" if no version is found
    return render_template('index.html', current_year=datetime.now().year, system_info=system_info, version=app_version)

@app.route('/api/products', methods=['GET'])

def get_products():
    if (config_data.get("data_source") == "db"): 
        conn = get_db_connection()
        cur = conn.cursor()
        cur.execute('SELECT * FROM products;')
        db_products = cur.fetchall()
        cur.close()
        conn.close()
        return jsonify(db_products), 200
    else:
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

