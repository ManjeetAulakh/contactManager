document.addEventListener('DOMContentLoaded', () => {
  // === 1. Initialize Theme Based on Saved Preference ===
  const html = document.documentElement;
  const label = document.getElementById('theme-label');
  const icon = document.getElementById('theme-icon');
  const savedTheme = localStorage.getItem('theme');

  // Apply stored theme on page load
  if (savedTheme === 'dark') {
    html.classList.add('dark');
    if (label && icon) {
      label.textContent = 'Light';
      icon.classList.replace('fa-moon', 'fa-sun');
    }
  } else {
    html.classList.remove('dark');
    if (label && icon) {
      label.textContent = 'Dark';
      icon.classList.replace('fa-sun', 'fa-moon');
    }
  }

  // === 2. Handle Theme Toggle Button Click ===
  const toggleBtn = document.getElementById('theme-toggle');
  if (toggleBtn && label && icon) {
    toggleBtn.addEventListener('click', () => {
      // Toggle dark mode class on <html>
      const isDark = html.classList.toggle('dark');
      // Save preference to localStorage
      localStorage.setItem('theme', isDark ? 'dark' : 'light');

      // Update button icon and label accordingly
      label.textContent = isDark ? 'Light' : 'Dark';
      icon.classList.toggle('fa-moon', !isDark);
      icon.classList.toggle('fa-sun', isDark);
    });
  }

  // === 3. Mobile Navbar Menu Toggle ===
  const mobileToggleBtn = document.getElementById('mobile-menu-button');
  const mobileMenu = document.getElementById('mobile-menu');
  if (mobileToggleBtn && mobileMenu) {
    mobileToggleBtn.addEventListener('click', () => {
      mobileMenu.classList.toggle('hidden');
    });
  }

  // === 4. User Profile Dropdown Toggle ===
  const userMenuBtn = document.getElementById('user-menu-button');
  const dropdown = document.getElementById('user-dropdown');
  if (userMenuBtn && dropdown) {
    // Show/hide dropdown on click
    userMenuBtn.addEventListener('click', () => {
      dropdown.classList.toggle('hidden');
    });

    // Close dropdown when clicking outside
    document.addEventListener('click', (e) => {
      if (!userMenuBtn.contains(e.target) && !dropdown.contains(e.target)) {
        dropdown.classList.add('hidden');
      }
    });
  }
});
