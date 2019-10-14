from flask import Flask, redirect, url_for, render_template, request
from flaskext.mysql import MySQL

#### Para iniciar el servidor/To Start the server
app = Flask(__name__)

# Configuraciones de MySQL/ MySQL Settings
mysql = MySQL()
app.config['MYSQL_DATABASE_USER'] = 'root'
app.config['MYSQL_DATABASE_PASSWORD'] = '12345678'
app.config['MYSQL_DATABASE_DB'] = 'frances'
app.config['MYSQL_DATABASE_HOST'] = 'localhost'
mysql.init_app(app)



@app.route('/')
def test():
    # Se crea la conexion de MySQL / MySQL connection is created
    conn = mysql.connect()
    # Se crea un cursor / A cursor is created
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM presente')
    data = cursor.fetchall()
    cursor.close()
    print(data)
    return render_template('presente.html')

@app.route('/evaluar', methods=['POST'])
def evaluar():
    if request.method == 'POST':
        je = request.form['je']
        tu = request.form['tu']
        il = request.form['il']
        nous = request.form['nous']
        vous = request.form['vous']
        ils = request.form['ils']
        print('Los valores del request son: \nje: ' + je + '\ntu: ' + tu + '\nil: ' + il + '\nnous: ' + nous + '\nvous: ' + vous + '\nils: ' + ils)
        return render_template('presente.html')


if __name__ == '__main__':
    app.run(debug=True)