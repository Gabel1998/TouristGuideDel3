function toggleForm() {
    var form = document.getElementById("addForm");
    form.classList.toggle("show");  // Toggle the 'show' class
}

// Ensure the form starts hidden on page load
document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("addForm");
    form.classList.remove("show");  // Ensure no 'show' class on load
});