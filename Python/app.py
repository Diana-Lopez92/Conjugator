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
    # Se retorna la vista html y los datos de la BD por medio de la variable "verbes"
    # HTML View is returned and the DB data through "verbes" variable
    return render_template('presente.html', verbes = data)

@app.route('/evaluar', methods=['POST'])
def evaluar():
    if request.method == 'POST':
        verbe = request.form['verbe']
        je = request.form['je']
        tu = request.form['tu']
        il = request.form['il']
        nous = request.form['nous']
        vous = request.form['vous']
        ils = request.form['ils']
        print('Los valores del request son: \nVerbo: ' + verbe + '\nje: ' + je + '\ntu: ' + tu + '\nil: ' + il + '\nnous: ' + nous + '\nvous: ' + vous + '\nils: ' + ils)
        
        # Se crea la conexión de MySQL / MySQL connection is created
        conn = mysql.connect()
        # Se crea un cursor / Acursor is created
        cursor = conn.cursor()
        # Se prepara la consulta / Query is prepared
        cursor.execute('SELECT * FROM presente WHERE verbo= %s', verbe)
        # Se ejecuta la consulta y se almacena / Query is executed and is storaged
        data = cursor.fetchall()
        print("INFO: ")
        print(data)
        # Se cierra la conexión / Connection is closed
        cursor.close()

        # Se revisan las respuestas con los datos de la BD
        # Answers are checked with the DB data
        # Se crea variable para llevar el control de aciertos
        # Variable is created to control the correct answers
        check = 0
        if je == data[0][2]:
            check += 1
        if tu == data[0][3]:
            check += 1
        if il == data[0][4]:
            check += 1
        if nous == data[0][5]:
            check += 1
        if vous == data[0][6]:
            check += 1
        if ils == data[0][7]:
            check += 1

        if check == 6:
            print('Felicidades, respondiste correctamente todo!!!')
        else:
            print('Lo siento, tuviste ' + str(6-check) + ' errores. Sigue practicando.')
        
        #####################################################
        print('DATA: ')
        # Para acceder a un elemento de una sublista se utilizan [][] / 
        # To access an element of a sublist it is used [][]
        print(data[0][1])
        ######################################################
        return redirect(url_for('test'))
        #return render_template('presente.html')


if __name__ == '__main__':
    app.run(debug=True)