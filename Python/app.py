from flask import Flask, redirect, url_for, render_template, request, flash
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

# Configuración de la sesión
# Session settings
app.secret_key = 'mySecretKey'

# Variables para el control de la evaluación
#answer1 = 3

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
    # Se crea la conexion de MySQL / MySQL connection is created
    conn = mysql.connect()
    # Se crea un cursor / A cursor is created
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM presente')
    dataList = cursor.fetchall()
    cursor.close()

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
        # Variables are created to control the correct answers
        check = 0
        
        if je == data[0][2]:
            check += 1
            answer1 = 1
        else:
            answer1 = 0
        if tu == data[0][3]:
            check += 1
            answer2 = 1
        else:
            answer2 = 0
        if il == data[0][4]:
            check += 1
            answer3 = 1
        else:
            answer3 = 0
        if nous == data[0][5]:
            check += 1
            answer4 = 1
        else:
            answer4 = 0
        if vous == data[0][6]:
            check += 1
            answer5 = 1
        else:
            answer5 = 0
        if ils == data[0][7]:
            check += 1
            answer6 = 1
        else:
            answer6 = 0

        if check == 6:
            print('Felicidades, respondiste correctamente todo!!!')
            flash('Félicitations, vous avez conjugué correctement !!!', 'success')
        else:
            print('Lo siento, tuviste ' + str(6-check) + ' errores. Sigue practicando.')
            flash('Je suis désolé, vous avez eu ' + str(6-check) + ' erreur(s). Continuez en pratiquant.', 'warning')
        #####################################################
        print('DATA: ')
        # Para acceder a un elemento de una sublista se utilizan [][] / 
        # To access an element of a sublist it is used [][]
        print(data[0][1])
        ######################################################
        #return redirect(url_for('test'))
        return render_template('presente.html', verbeList=verbe, je=je, tu=tu, il=il, nous=nous, vous=vous, ils=ils, answer1=answer1, answer2=answer2, answer3=answer3, answer4=answer4, answer5=answer5, answer6=answer6, verbes=dataList)


if __name__ == '__main__':
    app.run(debug=True)