# =====================================================
# CCS Sit-in Monitoring System - Python Flask Backend
# Simple beginner-friendly server
# Temporarily uses hardcoded/in-memory data (no database yet)
# =====================================================

from flask import Flask, request, redirect, render_template_string

app = Flask(__name__, static_folder='.', static_url_path='')

# ── Temporary in-memory storage (acts as a database for now) ──
students = [
    {
        "idNumber": "2024-00001",
        "firstName": "Juan",
        "lastName": "Dela Cruz",
        "middleName": "Santos",
        "courseLevel": "3",
        "password": "pass123",
        "email": "juan@email.com",
        "course": "BSIT",
        "address": "Cebu City"
    }
]


# ── Simple HTML templates for success and error responses ──
SUCCESS_PAGE = """
<!DOCTYPE html>
<html>
<head>
    <title>{{ title }}</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-content">
            <span class="site-title">College of Computer Studies Sit-in Monitoring System</span>
        </div>
    </nav>
    <div style="max-width:500px; margin:4rem auto; text-align:center;
                background:white; padding:2rem; border-radius:10px;
                box-shadow:0 2px 10px rgba(0,0,0,0.1);">
        <h2 style="color:#1e7e34; margin-bottom:1rem;">✓ {{ title }}</h2>
        <p style="color:#555; margin-bottom:2rem;">{{ message }}</p>
        <a href="{{ next_page }}" style="background:#0F3E7D; color:white;
           padding:0.8rem 2rem; border-radius:5px; text-decoration:none;
           font-weight:bold;">Go to Home →</a>
    </div>
</body>
</html>
"""

ERROR_PAGE = """
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-content">
            <span class="site-title">College of Computer Studies Sit-in Monitoring System</span>
        </div>
    </nav>
    <div style="max-width:500px; margin:4rem auto; text-align:center;
                background:white; padding:2rem; border-radius:10px;
                box-shadow:0 2px 10px rgba(0,0,0,0.1);">
        <h2 style="color:#c0392b; margin-bottom:1rem;">✗ Error</h2>
        <p style="color:#555; margin-bottom:2rem;">{{ message }}</p>
        <a href="{{ back_page }}" style="background:#0F3E7D; color:white;
           padding:0.8rem 2rem; border-radius:5px; text-decoration:none;
           font-weight:bold;">← Go Back</a>
    </div>
</body>
</html>
"""


# =====================================================
# ROUTE: Home Page
# =====================================================
@app.route('/')
def home():
    return app.send_static_file('index.html')


# =====================================================
# ROUTE: Register Page
# =====================================================
@app.route('/register.html')
def register_page():
    return app.send_static_file('register.html')


# =====================================================
# ROUTE: Handle Registration Form Submission
# =====================================================
@app.route('/register', methods=['POST'])
def register():
    # Get form data
    id_number      = request.form.get('idNumber', '').strip()
    first_name     = request.form.get('firstName', '').strip()
    last_name      = request.form.get('lastName', '').strip()
    middle_name    = request.form.get('middleName', '').strip()
    course_level   = request.form.get('courseLevel', '').strip()
    password       = request.form.get('password', '').strip()
    confirm_pass   = request.form.get('confirmPassword', '').strip()
    email          = request.form.get('email', '').strip()
    course         = request.form.get('course', '').strip()
    address        = request.form.get('address', '').strip()

    # ── Validation ──
    if not id_number:
        return render_template_string(ERROR_PAGE,
            message="ID Number is required!", back_page="/register.html")

    if len(password) < 6:
        return render_template_string(ERROR_PAGE,
            message="Password must be at least 6 characters!", back_page="/register.html")

    if password != confirm_pass:
        return render_template_string(ERROR_PAGE,
            message="Passwords do not match!", back_page="/register.html")

    if '@' not in email:
        return render_template_string(ERROR_PAGE,
            message="Invalid email format!", back_page="/register.html")

    if course_level not in ['1', '2', '3', '4']:
        return render_template_string(ERROR_PAGE,
            message="Please select a valid course level!", back_page="/register.html")

    # Check if ID is already registered
    for student in students:
        if student['idNumber'] == id_number:
            return render_template_string(ERROR_PAGE,
                message="ID Number already registered!", back_page="/register.html")

    # Save new student to in-memory list
    students.append({
        "idNumber":    id_number,
        "firstName":   first_name,
        "lastName":    last_name,
        "middleName":  middle_name,
        "courseLevel": course_level,
        "password":    password,
        "email":       email,
        "course":      course,
        "address":     address
    })

    print(f"✓ New student registered: {first_name} {last_name} | ID: {id_number}")

    return render_template_string(SUCCESS_PAGE,
        title="Registration Successful!",
        message=f"Welcome, {first_name}! You can now log in.",
        next_page="/")


# =====================================================
# ROUTE: Handle Login Form Submission
# =====================================================
@app.route('/login', methods=['POST'])
def login():
    id_number = request.form.get('loginId', '').strip()
    password  = request.form.get('loginPassword', '').strip()

    # Check against registered students
    for student in students:
        if student['idNumber'] == id_number and student['password'] == password:
            print(f"✓ Login successful: {student['firstName']} {student['lastName']}")
            return render_template_string(SUCCESS_PAGE,
                title="Login Successful!",
                message=f"Welcome back, {student['firstName']} {student['lastName']}!",
                next_page="/")

    # No match found
    print(f"✗ Failed login attempt for ID: {id_number}")
    return render_template_string(ERROR_PAGE,
        message="Invalid ID Number or Password!", back_page="/")


# =====================================================
# RUN THE SERVER
# =====================================================
if __name__ == '__main__':
    print("=================================")
    print("CCS Sit-in System Server Running!")
    print("Open: http://localhost:5000")
    print("=================================")
    print("Test login account:")
    print("  ID      : 2024-00001")
    print("  Password: pass123")
    print("=================================")
    app.run(debug=True)