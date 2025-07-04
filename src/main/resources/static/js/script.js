  // === Apply theme on page load ===
  document.addEventListener('DOMContentLoaded', () => {
    const html = document.documentElement;
    const label = document.getElementById('theme-label');
    const icon = document.getElementById('theme-icon');
    const savedTheme = localStorage.getItem('theme');

    if (savedTheme === 'dark') {
      html.classList.add('dark');
      if (label && icon) {
        label.textContent = 'Light';
        icon.classList.remove('fa-moon');
        icon.classList.add('fa-sun');
      }
    } else {
      html.classList.remove('dark');
      if (label && icon) {
        label.textContent = 'Dark';
        icon.classList.remove('fa-sun');
        icon.classList.add('fa-moon');
      }
    }
  });

  // === Toggle button click handling ===
  const toggleBtn = document.getElementById('theme-toggle');
  const label = document.getElementById('theme-label');
  const icon = document.getElementById('theme-icon');

  if (toggleBtn && label && icon) {
    toggleBtn.addEventListener('click', () => {
      const html = document.documentElement;
      html.classList.toggle('dark');

      const isDark = html.classList.contains('dark');
      localStorage.setItem('theme', isDark ? 'dark' : 'light');

      if (isDark) {
        label.textContent = 'Light';
        icon.classList.remove('fa-moon');
        icon.classList.add('fa-sun');
      } else {
        label.textContent = 'Dark';
        icon.classList.remove('fa-sun');
        icon.classList.add('fa-moon');
      }
    });
  }

  // === Mobile menu toggle ===
  const mobileToggleBtn = document.getElementById('mobile-menu-button');
  const mobileMenu = document.getElementById('mobile-menu');

  if (mobileToggleBtn && mobileMenu) {
    mobileToggleBtn.addEventListener('click', () => {
      mobileMenu.classList.toggle('hidden');
    });
  }
