# Marwadi University Website - Deployment Guide

## Project Overview
A modern, responsive university website built with HTML, CSS, JavaScript for the frontend and Java Servlets for backend integration.

## Project Structure
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
└── README.md
```

## Features

### Pages Included
1. **Home (index.html)** - Landing page with hero section, features, statistics, and news
2. **About (about.html)** - University information, vision, mission, and facilities
3. **Academics (academics.html)** - Academic programs, courses, and calendar
4. **Admissions (admissions.html)** - Admission process, eligibility, fees, and application form
5. **Faculty (faculty.html)** - Faculty profiles and achievements
6. **Contact (contact.html)** - Contact information and inquiry form

### Backend Servlets
1. **ContactServlet** - Handles contact form submissions
2. **AdmissionServlet** - Processes admission applications

### Technologies Used
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Backend**: Java Servlets (Servlet API 4.0)
- **Server**: Apache Tomcat 9.0+ or any Servlet Container

## Prerequisites
- JDK 8 or higher
- Apache Tomcat 9.0+ or any Java EE servlet container
- Maven (optional, for building)

## Deployment Instructions

### Method 1: Using Apache Tomcat

1. **Install Apache Tomcat**
   - Download from https://tomcat.apache.org/
   - Extract to desired location
   - Set CATALINA_HOME environment variable

2. **Compile Java Servlets**
   ```bash
   # Navigate to the project directory
   cd src/main/java
   
   # Compile servlets (ensure servlet-api.jar is in classpath)
   javac -cp /path/to/tomcat/lib/servlet-api.jar com/marwadi/servlets/*.java
   ```

3. **Create WAR file structure**
   ```bash
   # Create directories
   mkdir -p build/WEB-INF/classes
   
   # Copy compiled classes
   cp -r com build/WEB-INF/classes/
   
   # Copy web.xml
   cp src/main/webapp/WEB-INF/web.xml build/WEB-INF/
   
   # Copy web resources
   cp -r src/main/webapp/* build/
   ```

4. **Deploy to Tomcat**
   - Copy the entire `build` folder to `TOMCAT_HOME/webapps/marwadi-university/`
   - Or create a WAR file and deploy:
     ```bash
     cd build
     jar -cvf marwadi-university.war *
     cp marwadi-university.war TOMCAT_HOME/webapps/
     ```

5. **Start Tomcat**
   ```bash
   # On Linux/Mac
   $CATALINA_HOME/bin/startup.sh
   
   # On Windows
   %CATALINA_HOME%\bin\startup.bat
   ```

6. **Access the Website**
   - Open browser and navigate to: `http://localhost:8080/marwadi-university/`

### Method 2: Using Maven

1. **Create pom.xml** (if using Maven)
   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0">
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.marwadi</groupId>
       <artifactId>marwadi-university</artifactId>
       <version>1.0</version>
       <packaging>war</packaging>
       
       <dependencies>
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <version>4.0.1</version>
               <scope>provided</scope>
           </dependency>
       </dependencies>
   </project>
   ```

2. **Build and Deploy**
   ```bash
   mvn clean package
   cp target/marwadi-university.war TOMCAT_HOME/webapps/
   ```

## Configuration

### Servlet Mappings (web.xml)
- `/contact-submit` - Contact form handler
- `/admission-submit` - Admission form handler

### Database Integration (Optional)
To add database support:
1. Add JDBC driver to WEB-INF/lib/
2. Configure database connection in servlets
3. Create database tables for contact and admission data

Example connection code:
```java
Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/marwadi_db", 
    "username", 
    "password"
);
```

## Testing

1. **Test Home Page**
   - Navigate to: `http://localhost:8080/marwadi-university/`
   - Verify all sections load properly

2. **Test Navigation**
   - Click through all navigation links
   - Verify mobile menu works on smaller screens

3. **Test Forms**
   - Submit contact form at `/pages/contact.html`
   - Submit admission form at `/pages/admissions.html`
   - Verify success/error messages display correctly

4. **Test Responsive Design**
   - Resize browser window
   - Test on mobile devices
   - Verify all elements are properly responsive

## Customization

### Changing Colors
Edit CSS variables in `src/main/webapp/css/style.css`:
```css
:root {
    --primary-color: #003366;
    --secondary-color: #0066cc;
    --accent-color: #ff6600;
}
```

### Adding New Pages
1. Create HTML file in `src/main/webapp/pages/`
2. Follow existing page structure
3. Add navigation link in header
4. Update footer links

### Modifying Backend Logic
1. Edit servlet files in `src/main/java/com/marwadi/servlets/`
2. Add database integration as needed
3. Recompile and redeploy

## Production Deployment

1. **Security Considerations**
   - Enable HTTPS
   - Implement CSRF protection
   - Add input sanitization
   - Use prepared statements for database queries
   - Implement authentication for admin areas

2. **Performance Optimization**
   - Enable compression in web.xml
   - Minify CSS and JavaScript
   - Optimize images
   - Enable browser caching
   - Use CDN for static resources

3. **Monitoring**
   - Set up logging
   - Monitor servlet response times
   - Track form submissions
   - Monitor server resources

## Support

For questions or issues:
- Email: info@marwadi.edu.in
- Phone: +91 98765 43210

## License
MIT License - See LICENSE file for details
