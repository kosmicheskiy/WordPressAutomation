package FrameworkPack.Pages.PostsDirectory;

import org.openqa.selenium.By;

import FrameworkPack.Navigation.LeftNavigation;

public class PostPageStateNavigation {

	static By draftStateLink = By.cssSelector(".draft>a");
	static By publishStateLink = By.cssSelector(".publish>a");
	static By binStateLink = By.cssSelector(".trash>a");
	static By pendingStateLink = By.cssSelector(".pending>a");

	public static class Draft {
		public static void Select() {
			PostPageStateMenuSelector.Select(draftStateLink);
		}
	}

	public static class Published {
		public static void Select() {
			PostPageStateMenuSelector.Select(publishStateLink);
		}
	}

	public static class All {
		public static void Select() {
			LeftNavigation.Posts.AllPosts.Select();
		}
	}

	public static class Pending {
		public static void Select() {
			PostPageStateMenuSelector.Select(pendingStateLink);
		}
	}

	public static class Bin {
		public static void Select() {
			PostPageStateMenuSelector.Select(binStateLink);
		}
	}
}
