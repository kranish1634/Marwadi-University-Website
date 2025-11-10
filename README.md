# Marwadi University Website

A modern, fully responsive university website built with HTML5, CSS3, JavaScript, and Java Servlets.

## 🌟 Features

### Pages
- **Home** - Hero section, features showcase, statistics, and latest news
- **About** - University vision, mission, values, and campus facilities
- **Academics** - Comprehensive list of programs, courses, and academic calendar
- **Admissions** - Admission process, eligibility criteria, fee structure, and online application form
- **Faculty** - Faculty profiles, achievements, and research activities
- **Contact** - Contact information, inquiry form, and department details

### Technical Features
- ✅ Fully responsive design (mobile, tablet, desktop)
- ✅ Interactive navigation with mobile menu
- ✅ Form validation (client-side and server-side)
- ✅ Java Servlets for backend form processing
- ✅ Smooth animations and transitions
- ✅ SEO-friendly structure
- ✅ Cross-browser compatible
- ✅ Professional UI/UX design

## 🛠️ Technology Stack

**Frontend:**
- HTML5
- CSS3 (with CSS Variables and Flexbox/Grid)
- JavaScript (ES6+)

**Backend:**
- Java Servlets (Servlet API 4.0)
- Java 8+

**Server:**
- Apache Tomcat 9.0+ (or any Java EE servlet container)

## 📁 Project Structure

```
Marwadi-University-Website/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── marwadi/
│       │           └── servlets/
│       │               ├── ContactServlet.java
│       │               └── AdmissionServlet.java
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml
│           ├── css/
│           │   └── style.css
│           ├── js/
│           │   └── main.js
│           ├── pages/
│           │   ├── about.html
│           │   ├── academics.html
│           │   ├── admissions.html
│           │   ├── faculty.html
│           │   └── contact.html
│           ├── index.html
│           └── error.html
├── pom.xml
├── DEPLOYMENT.md
├── verify.sh
└── README.md
```

## 🚀 Quick Start

### Prerequisites
- JDK 8 or higher
- Apache Tomcat 9.0+ or any servlet container
- Maven (optional, for building)

### Installation & Deployment

#### Option 1: Using Maven
```bash
# Clone the repository
git clone https://github.com/kranish1634/Marwadi-University-Website.git
cd Marwadi-University-Website

# Build the project
mvn clean package

# Deploy the generated WAR file
cp target/marwadi-university.war $TOMCAT_HOME/webapps/

# Start Tomcat
$TOMCAT_HOME/bin/startup.sh  # Linux/Mac
# or
%TOMCAT_HOME%\bin\startup.bat  # Windows

# Access the website
# Open browser: http://localhost:8080/marwadi-university/
```

#### Option 2: Manual Deployment
See [DEPLOYMENT.md](DEPLOYMENT.md) for detailed manual deployment instructions.

### Verification
Run the verification script to check all components:
```bash
chmod +x verify.sh
./verify.sh
```

## 📝 Configuration

### Servlet Mappings
- `/contact-submit` - Handles contact form submissions
- `/admission-submit` - Processes admission applications

### Customization
Edit CSS variables in `src/main/webapp/css/style.css`:
```css
:root {
    --primary-color: #003366;
    --secondary-color: #0066cc;
    --accent-color: #ff6600;
}
```

## 📊 Code Statistics
- **Total Lines**: ~2,676 lines
- HTML: 1,824 lines
- CSS: 417 lines
- JavaScript: 152 lines
- Java: 283 lines

## 🌐 Browser Support
- Chrome (latest)
- Firefox (latest)
- Safari (latest)
- Edge (latest)
- Mobile browsers (iOS Safari, Chrome Mobile)

## 📱 Responsive Breakpoints
- Desktop: 1200px+
- Tablet: 768px - 1199px
- Mobile: < 768px

## 🔒 Security Features
- Input validation (client and server-side)
- HTML escaping to prevent XSS
- Error handling
- Secure servlet implementation

## 📖 Documentation
- [DEPLOYMENT.md](DEPLOYMENT.md) - Comprehensive deployment guide
- Inline code comments in servlets
- HTML semantic structure

## 🤝 Contributing
Contributions are welcome! Please feel free to submit pull requests.

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Contact
- **Email**: info@marwadi.edu.in
- **Phone**: +91 98765 43210
- **Address**: Rajkot-Morbi Highway, Rajkot, Gujarat 360003

## 🙏 Acknowledgments
- Built for Marwadi University
- Designed with modern web standards
- Follows responsive design best practices
