package Workflows;

import java.util.Random;

import FrameworkPack.Pages.PostsDirectory.NewPostPage;
import FrameworkPack.Utilities.ListPostPage;
import FrameworkPack.Utilities.PostType;

public class PostCreator {

	public static String PreviousTitle;
	public static String PreviousBody;

	public static String getPreviousBody() {
		return PreviousBody;
	}

	public static void setPreviousBody(String previousBody) {
		PreviousBody = previousBody;
	}

	public static String getPreviousTitle() {
		return PreviousTitle;
	}

	public static void setPreviousTitle(String previousTitle) {
		PreviousTitle = previousTitle;
	}

	public static void CreatePost() {
		
		Initialize();

		NewPostPage.GoTo();

		PreviousTitle = CreateTitle();
		PreviousBody = CreateBody();

		NewPostPage.CreatePost(PreviousTitle).WithBody(PreviousBody).Publish();

	}

	public static void CreatePostWithCategory() throws InterruptedException {
		
		Initialize();

		NewPostPage.GoTo();

		PreviousTitle = CreateTitle();
		PreviousBody = CreateBody();

		NewPostPage.CreatePost(PreviousTitle).WithBody(PreviousBody).PublishWithCategory();

	}

	private static String CreateTitle() {

		return (CreateRandomText() + ", title");
	}

	private static String CreateBody() {

		return (CreateRandomText() + ", body");
	}

	private static String CreateRandomText() {

		StringBuilder s = new StringBuilder();

		String[] Words = { "boy", "cat", "dog", "rabbit", "basketball", "throw", "find", "scary" };
		String[] Articles = { "the", "a", "an", "of", "to", "it", "as" };

		for (int i = 0; i < Math.random() * 3; i++) {
			int idx = new Random().nextInt(Words.length);
			String random = (Words[idx]);
			s.append(random);
			s.append(" ");

			int idx1 = new Random().nextInt(Articles.length);
			String random1 = (Articles[idx1]);
			s.append(random1);
			s.append(" ");
		}
		return s.toString();

	}

	public static void Initialize() {
		PreviousTitle = null;
		PreviousBody = null;
	}

	public static void CleanUp() {
		if (CreatedAPost()) {
			TrashPost();
		}
	}

	private static void TrashPost() {
		ListPostPage.Operations.Trash(PreviousTitle);
		Initialize();

	}

	private static boolean CreatedAPost() {

		return !(PreviousTitle != null && PreviousTitle.isEmpty());

	}

}
