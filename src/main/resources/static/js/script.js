  document.addEventListener('DOMContentLoaded', () => {
    console.log("✅ Global script loaded");

    // === 1. Theme Setup
    const html = document.documentElement;
    const label = document.getElementById('theme-label');
    const icon = document.getElementById('theme-icon');
    const savedTheme = localStorage.getItem('theme');

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

    const toggleBtn = document.getElementById('theme-toggle');
    if (toggleBtn && label && icon) {
      toggleBtn.addEventListener('click', () => {
        const isDark = html.classList.toggle('dark');
        localStorage.setItem('theme', isDark ? 'dark' : 'light');
        label.textContent = isDark ? 'Light' : 'Dark';
        icon.classList.toggle('fa-moon', !isDark);
        icon.classList.toggle('fa-sun', isDark);
      });
    }

    // === 2. Mobile Menu Toggle
    const mobileToggleBtn = document.getElementById('mobile-menu-button');
    const mobileMenu = document.getElementById('mobile-menu');
    if (mobileToggleBtn && mobileMenu) {
      mobileToggleBtn.addEventListener('click', () => {
        mobileMenu.classList.toggle('hidden');
      });
    }

    // === 3. User Dropdown
    const userMenuBtn = document.getElementById('user-menu-button');
    const dropdown = document.getElementById('user-dropdown');
    if (userMenuBtn && dropdown) {
      userMenuBtn.addEventListener('click', () => {
        dropdown.classList.toggle('hidden');
      });

      document.addEventListener('click', (e) => {
        if (!userMenuBtn.contains(e.target) && !dropdown.contains(e.target)) {
          dropdown.classList.add('hidden');
        }
      });
    }

    // === 4. Sidebar Toggle
    const sidebarToggleBtn = document.getElementById('sidebar-toggle-button');
    const sidebar = document.getElementById('user-sidebar');
    const overlay = document.getElementById('sidebar-overlay');

    if (!sidebarToggleBtn || !sidebar || !overlay) {
      console.warn("⚠️ Sidebar toggle setup failed - elements not found");
    } else {
      sidebarToggleBtn.addEventListener('click', () => {
        console.log("➡️ Sidebar toggle clicked");
        sidebar.classList.toggle('-translate-x-full');
        sidebar.classList.toggle('translate-x-0');
        overlay.classList.toggle('hidden');
      });

      overlay.addEventListener('click', () => {
        console.log("⬅️ Overlay clicked - hiding sidebar");
        sidebar.classList.add('-translate-x-full');
        sidebar.classList.remove('translate-x-0');
        overlay.classList.add('hidden');
      });
    }
  });
