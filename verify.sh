#!/bin/bash

echo "=========================================="
echo "Marwadi University Website Verification"
echo "=========================================="
echo ""

# Check project structure
echo "1. Checking project structure..."
required_dirs=(
    "src/main/java/com/marwadi/servlets"
    "src/main/webapp/WEB-INF"
    "src/main/webapp/css"
    "src/main/webapp/js"
    "src/main/webapp/pages"
)

for dir in "${required_dirs[@]}"; do
    if [ -d "$dir" ]; then
        echo "   ✓ $dir exists"
    else
        echo "   ✗ $dir missing"
        exit 1
    fi
done
echo ""

# Check HTML files
echo "2. Checking HTML files..."
html_files=(
    "src/main/webapp/index.html"
    "src/main/webapp/pages/about.html"
    "src/main/webapp/pages/academics.html"
    "src/main/webapp/pages/admissions.html"
    "src/main/webapp/pages/faculty.html"
    "src/main/webapp/pages/contact.html"
    "src/main/webapp/error.html"
)

for file in "${html_files[@]}"; do
    if [ -f "$file" ]; then
        echo "   ✓ $file exists"
    else
        echo "   ✗ $file missing"
        exit 1
    fi
done
echo ""

# Check Java servlets
echo "3. Checking Java servlets..."
java_files=(
    "src/main/java/com/marwadi/servlets/ContactServlet.java"
    "src/main/java/com/marwadi/servlets/AdmissionServlet.java"
)

for file in "${java_files[@]}"; do
    if [ -f "$file" ]; then
        echo "   ✓ $file exists"
    else
        echo "   ✗ $file missing"
        exit 1
    fi
done
echo ""

# Check CSS and JS
echo "4. Checking CSS and JavaScript files..."
if [ -f "src/main/webapp/css/style.css" ]; then
    echo "   ✓ style.css exists"
else
    echo "   ✗ style.css missing"
    exit 1
fi

if [ -f "src/main/webapp/js/main.js" ]; then
    echo "   ✓ main.js exists"
else
    echo "   ✗ main.js missing"
    exit 1
fi
echo ""

# Check web.xml
echo "5. Checking web.xml configuration..."
if [ -f "src/main/webapp/WEB-INF/web.xml" ]; then
    echo "   ✓ web.xml exists"
    # Check for servlet mappings
    if grep -q "ContactServlet" "src/main/webapp/WEB-INF/web.xml" && \
       grep -q "AdmissionServlet" "src/main/webapp/WEB-INF/web.xml"; then
        echo "   ✓ Servlet mappings configured"
    else
        echo "   ✗ Servlet mappings missing"
        exit 1
    fi
else
    echo "   ✗ web.xml missing"
    exit 1
fi
echo ""

# Count lines of code
echo "6. Code statistics..."
echo "   HTML files: $(cat src/main/webapp/*.html src/main/webapp/pages/*.html 2>/dev/null | wc -l) lines"
echo "   CSS files: $(cat src/main/webapp/css/*.css 2>/dev/null | wc -l) lines"
echo "   JavaScript files: $(cat src/main/webapp/js/*.js 2>/dev/null | wc -l) lines"
echo "   Java files: $(cat src/main/java/com/marwadi/servlets/*.java 2>/dev/null | wc -l) lines"
echo ""

echo "=========================================="
echo "✓ All verification checks passed!"
echo "=========================================="
echo ""
echo "The website is ready for deployment."
echo "See DEPLOYMENT.md for deployment instructions."
